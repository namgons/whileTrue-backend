package com.whiletruebackend.global.utils;

import com.whiletruebackend.global.error.exception.AuthInvalidTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.UUID;

public class JwtUtils {

    public static String createJwt(UUID memberId, String secretKey, Long expiredMs) {
        Claims claims = Jwts.claims();
        claims.put("member_id", memberId);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiredMs))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public static boolean isExpired(String token, String secretKey) {
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

    public static String getMemberIdFromJwt(String token, String secretKey) {
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
}
