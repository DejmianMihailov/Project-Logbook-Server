/*package com.flight.logbook_server.security;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.util.Base64;

public class GenerateSecureKey {
    public static void main(String[] args) {
        // Генерирайте сигурен ключ за HS512
        String secretKey = Base64.getEncoder().encodeToString(Keys.secretKeyFor(SignatureAlgorithm.HS512).getEncoded());
        System.out.println("Generated Key: " + secretKey);
    }
} */
