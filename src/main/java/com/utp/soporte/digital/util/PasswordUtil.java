package com.utp.soporte.digital.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = "admin123";
        String currentHash = "$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW";
        
        // Test current hash
        System.out.println("Testing current hash...");
        System.out.println("Password: " + password);
        System.out.println("Current Hash: " + currentHash);
        System.out.println("Matches current hash: " + encoder.matches(password, currentHash));
        
        // Generate new hash
        String newHash = encoder.encode(password);
        System.out.println("\nGenerating new hash...");
        System.out.println("New Hash: " + newHash);
        System.out.println("Matches new hash: " + encoder.matches(password, newHash));
    }
} 