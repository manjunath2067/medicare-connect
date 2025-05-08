package com.example.medicare.connect.util;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.example.medicare.connect.model.Users;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    private Key getSigningKey() {
        try {
            // Validate the jwtSecret before using it
            if (jwtSecret == null || jwtSecret.isEmpty()) {
                throw new IllegalArgumentException("JWT secret key is not provided or is empty.");
            }

            // Log the secret (just for debugging, do not use in production)
            System.out.println("Using JWT Secret: " + jwtSecret);

            // Convert the secret into a byte array and generate the signing key
            byte[] keyBytes = jwtSecret.getBytes();
            return Keys.hmacShaKeyFor(keyBytes);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid JWT secret key: " + e.getMessage());
            throw e; // Rethrow the exception so it's clear where the problem is
        } catch (Exception e) {
            System.out.println("Unexpected error while generating signing key: " + e.getMessage());
            throw new RuntimeException("Error occurred while generating signing key", e);
        }
    }


    public String generateToken(Users users) {
        Key signingKey = getSigningKey();

        return Jwts.builder()
              .setSubject(users.getEmail())
              .claim("role", users.getRole().name())
              .setIssuedAt(new Date())
              .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
              .signWith(signingKey)
              .compact();
    }

    public String extractRole(String token) {
        Claims claims = parseClaims(token);
        return claims.get("role", String.class);
    }
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public String extractUsername(String token) {
        Claims claims = parseClaims(token);
        return claims.getSubject();
    }

    private Claims parseClaims(String token) {
        Key signingKey = getSigningKey();
        return Jwts.parser()
              .setSigningKey(signingKey)
              .build()
              .parseClaimsJws(token)
              .getBody();
    }

    private boolean isTokenExpired(String token) {
        Date expiration = parseClaims(token).getExpiration();
        return expiration.before(new Date());
    }
}
