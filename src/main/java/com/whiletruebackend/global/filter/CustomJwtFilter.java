package com.whiletruebackend.global.filter;

import com.whiletruebackend.domain.Member.entity.Member;
import com.whiletruebackend.domain.Member.repository.MemberRepository;
import com.whiletruebackend.global.error.exception.AuthInvalidAuthorizationFormatException;
import com.whiletruebackend.global.error.exception.AuthNoAuthorizationException;
import com.whiletruebackend.global.error.exception.AuthTokenExpiredException;
import com.whiletruebackend.global.error.exception.MemberNotFoundException;
import com.whiletruebackend.global.utils.AuthHelper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class CustomJwtFilter extends OncePerRequestFilter {

    private final MemberRepository memberRepository;
    private final AuthHelper authHelper;
    private final String secretKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authorization == null) {
            throw AuthNoAuthorizationException.EXCEPTION;
        }

        if (!authorization.startsWith("Bearer ")) {
            throw AuthInvalidAuthorizationFormatException.EXCEPTION;
        }

        String token = authorization.split(" ")[1];

        if (authHelper.isExpired(token, secretKey)) {
            throw AuthTokenExpiredException.EXCEPTION;
        }

        String memberId = authHelper.getMemberIdFromJwt(token, secretKey);

        Member member = memberRepository.findById(memberId).orElseThrow(() -> MemberNotFoundException.EXCEPTION);

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(member, null, List.of(new SimpleGrantedAuthority("USER")));

        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }

}
