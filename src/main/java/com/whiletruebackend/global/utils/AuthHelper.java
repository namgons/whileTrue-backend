package com.whiletruebackend.global.utils;

import com.whiletruebackend.domain.Member.dto.TokenDto;
import com.whiletruebackend.domain.Member.entity.Member;
import com.whiletruebackend.domain.Member.repository.MemberRepository;
import com.whiletruebackend.global.error.exception.*;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
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

    private final String REFRESH_TOKEN_COOKIE_NAME = "refresh-token";

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
        try {
            SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

            return Jwts.builder()
                    .claim("member_id", memberId)
                    .issuedAt(new Date(System.currentTimeMillis()))
                    .expiration(new Date(System.currentTimeMillis() + expiredMs))
                    .signWith(key)
                    .compact();
        } catch (Exception e) {
            throw AuthTokenCreationErrorException.EXCEPTION;
        }
    }

    public boolean isExpired(String token, String secretKey, String type) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
            return Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getExpiration()
                    .before(new Date());
        } catch (ExpiredJwtException e) {
            if (type.equals("accessToken")) {
                throw AuthTokenExpiredException.EXCEPTION;
            } else {
                throw AuthRefreshTokenExpiredException.EXCEPTION;
            }
        } catch (Exception e) {
            throw AuthInvalidTokenException.EXCEPTION;
        }
    }

    public String getMemberIdFromJwt(String token, String secretKey, String type) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
            return Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .get("member_id", String.class);
        } catch (ExpiredJwtException e) {
            if (type.equals("accessToken")) {
                throw AuthTokenExpiredException.EXCEPTION;
            } else {
                throw AuthRefreshTokenExpiredException.EXCEPTION;
            }
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
    public TokenDto refreshToken(String refreshToken) {
        if (refreshToken == null) {
            throw AuthRefreshTokenNotFoundException.EXCEPTION;
        }
        if (isExpired(refreshToken, secretKey, "refreshToken")) {
            throw AuthRefreshTokenExpiredException.EXCEPTION;
        }

        String memberId = getMemberIdFromJwt(refreshToken, secretKey, "refreshToken");
        Member member = memberRepository.findById(memberId).orElseThrow(() -> MemberNotFoundException.EXCEPTION);
        return new TokenDto(createAccessToken(member), createRefreshToken(member));
    }
}
