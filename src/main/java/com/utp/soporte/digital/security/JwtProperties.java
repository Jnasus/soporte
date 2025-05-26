package com.utp.soporte.digital.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties(prefix = "app.jwt")
public class JwtProperties {
    private String secretKey;
    private Long accessTokenValidityInMinutes;
    private Long refreshTokenValidityInDays;
    // Getters y Setters
    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public long getAccessTokenValidityInMinutes() {
        return accessTokenValidityInMinutes;
    }

    public void setAccessTokenValidityInMinutes(long accessTokenValidityInMinutes) {
        this.accessTokenValidityInMinutes = accessTokenValidityInMinutes;
    }

    public long getRefreshTokenValidityInDays() {
        return refreshTokenValidityInDays;
    }

    public void setRefreshTokenValidityInDays(long refreshTokenValidityInDays) {
        this.refreshTokenValidityInDays = refreshTokenValidityInDays;
    }
} 