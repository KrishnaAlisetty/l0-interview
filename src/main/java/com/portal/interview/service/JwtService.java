/**
 * @author Krishna Alisetty
 * @date 26-12-2025
 */

package com.portal.interview.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {
    private static final String SECRET =
            "this-is-a-very-secure-256-bit-secret-key-for-jwt-signing";

    private static final long EXPIRATION_MS = 60 * 60 * 1000; // 1 hour

    private final SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes());

    /* =======================
       TOKEN GENERATION
       ======================= */

    public String generateToken(
            String userId,
            String email,
            String role,
            String roomId
    ) {

        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
                .addClaims(Map.of(
                        "email", email,
                        "role", role,
                        "room", roomId
                ))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /* =======================
       TOKEN VALIDATION
       ======================= */

    public Claims parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /* =======================
       CLAIM HELPERS
       ======================= */

    public String extractUserId(String token) {
        return parseToken(token).getSubject();
    }

    public String extractEmail(String token) {
        return parseToken(token).get("email", String.class);
    }

    public String extractRole(String token) {
        return parseToken(token).get("role", String.class);
    }

    public String extractRoom(String token) {
        return parseToken(token).get("room", String.class);
    }

    public boolean isTokenExpired(String token) {
        return parseToken(token).getExpiration().before(new Date());
    }
}
