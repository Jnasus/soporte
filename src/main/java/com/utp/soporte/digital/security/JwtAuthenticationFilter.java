package com.utp.soporte.digital.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;

    @Autowired
    public JwtAuthenticationFilter(JwtService jwtService, CustomUserDetailsService customUserDetailsService) {
        this.jwtService = jwtService;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            final String authHeader = request.getHeader("Authorization");
            logger.debug("Processing request to {} with method {}", request.getRequestURI(), request.getMethod());

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                logger.debug("No Bearer token found in request");
                filterChain.doFilter(request, response);
                return;
            }

            final String jwt = authHeader.substring(7);
            final String userEmail = jwtService.extractUsername(jwt);
            logger.debug("Extracted email from token: {}", userEmail);

            if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                logger.debug("Loading user details for email: {}", userEmail);
                UserDetails userDetails = customUserDetailsService.loadUserByUsername(userEmail);
                logger.debug("User authorities: {}", userDetails.getAuthorities());
                
                if (jwtService.isTokenValid(jwt, userDetails)) {
                    logger.debug("Token is valid for user: {}", userEmail);
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    logger.debug("Authentication successful for user: {} with authorities: {}", 
                               userEmail, userDetails.getAuthorities());
                } else {
                    logger.warn("Invalid token for user: {}", userEmail);
                }
            }

            filterChain.doFilter(request, response);
        } catch (Exception e) {
            logger.error("Error processing JWT token: {}", e.getMessage(), e);
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error processing JWT token");
        }
    }
} 