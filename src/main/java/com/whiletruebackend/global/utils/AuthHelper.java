package com.whiletruebackend.global.utils;

import com.whiletruebackend.domain.Member.dto.TokenDto;
import com.whiletruebackend.domain.Member.entity.Member;
import com.whiletruebackend.domain.Member.repository.MemberRepository;
import com.whiletruebackend.global.error.exception.AuthInvalidTokenException;
import com.whiletruebackend.global.error.exception.AuthRefreshTokenExpiredException;
import com.whiletruebackend.global.error.exception.AuthRefreshTokenNotFoundException;
import com.whiletruebackend.global.error.exception.MemberNotFoundException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class AuthHelper {

    @Value("${jwt.secret}")
    private String secretKey;
    @Value("${jwt.access_token_expiry}")
    private Long accessTokenExpiry;
    @Value("${jwt.refresh_token_expiry}")
    private Long refreshTokenExpiry;

    private final MemberRepository memberRepository;

    private final String REFRESH_TOKEN_COOKIE_NAME = "refreshToken";

    public String createAccessToken(Member member) {
        return createToken(member.getId(), secretKey, accessTokenExpiry);
    }

    @Transactional
    public String createRefreshToken(Member member) {
        String refreshToken = createToken(member.getId(), secretKey, refreshTokenExpiry);
        member.updateRefreshToken(refreshToken);
        memberRepository.save(member);
        return refreshToken;
    }

    private String createToken(String memberId, String secretKey, Long expiredMs) {
        Claims claims = Jwts.claims();
        claims.put("member_id", memberId);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiredMs))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public boolean isExpired(String token, String secretKey) {
        try {
            return Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody()
                    .getExpiration()
                    .before(new Date());
        } catch (Exception e) {
            throw AuthInvalidTokenException.EXCEPTION;
        }
    }

    public String getMemberIdFromJwt(String token, String secretKey) {
        try {
            return Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody()
                    .get("member_id", String.class);
        } catch (Exception e) {
            throw AuthInvalidTokenException.EXCEPTION;
        }
    }

    public Cookie createCookie(String refreshToken) {
        Cookie cookie = new Cookie(REFRESH_TOKEN_COOKIE_NAME, refreshToken);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(Math.toIntExact(refreshTokenExpiry));
        cookie.setPath("/");
        return cookie;
    }

    @Transactional
    public TokenDto refreshToken(HttpServletRequest request) {
        Cookie cookie = null;
        for (Cookie targetCookie : request.getCookies()) {
            if (targetCookie.getName().equals(REFRESH_TOKEN_COOKIE_NAME)) {
                cookie = targetCookie;
                break;
            }
        }

        if (cookie == null) {
            throw AuthRefreshTokenNotFoundException.EXCEPTION;
        }

        String refreshToken = cookie.getAttribute(REFRESH_TOKEN_COOKIE_NAME);

        if (isExpired(refreshToken, secretKey)) {
            throw AuthRefreshTokenExpiredException.EXCEPTION;
        }

        String memberId = getMemberIdFromJwt(refreshToken, secretKey);
        Member member = memberRepository.findById(memberId).orElseThrow(() -> MemberNotFoundException.EXCEPTION);
        return new TokenDto(createAccessToken(member), createRefreshToken(member));
    }
}
