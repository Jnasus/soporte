package com.utp.soporte.digital.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import com.utp.soporte.digital.security.JwtProperties;

@Configuration
@EnableConfigurationProperties(JwtProperties.class)
public class AppConfig {
} 