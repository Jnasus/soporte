package com.utp.soporte.digital.security;

import com.utp.soporte.digital.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

@Service
public class JwtService {
    private static final Logger logger = LoggerFactory.getLogger(JwtService.class);
    private final JwtProperties jwtProperties;

    public JwtService(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    public String generateToken(Usuario usuario) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", usuario.getRole());
        claims.put("email", usuario.getEmail());
        return generateToken(claims, usuario);
    }

    public String generateToken(Map<String, Object> extraClaims, Usuario usuario) {
        logger.debug("Generating token for user: {}", usuario.getEmail());
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + (jwtProperties.getAccessTokenValidityInMinutes() * 60 * 1000));
        
        String token = Jwts.builder()
                .setSubject(usuario.getEmail())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .setId(UUID.randomUUID().toString())
                .addClaims(extraClaims)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
        
        logger.debug("Generated token: {}", token);
        return token;
    }

    public String generateRefreshToken(Usuario usuario) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("type", "refresh");
        claims.put("role", usuario.getRole());
        return buildToken(claims, usuario, jwtProperties.getRefreshTokenValidityInDays(), ChronoUnit.DAYS);
    }

    private String buildToken(Map<String, Object> extraClaims, UserDetails userDetails, long validity, ChronoUnit unit) {
        Instant now = Instant.now();
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(validity, unit)))
                .setId(UUID.randomUUID().toString())
                .addClaims(extraClaims)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        try {
            final String username = extractUsername(token);
            logger.debug("Validating token for user: {}", username);
            boolean isValid = (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
            logger.debug("Token validation result: {}", isValid);
            return isValid;
        } catch (Exception e) {
            logger.error("Error validating token: {}", e.getMessage());
            return false;
        }
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSigningKey() {
        byte[] keyBytes = jwtProperties.getSecretKey().getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }
} 