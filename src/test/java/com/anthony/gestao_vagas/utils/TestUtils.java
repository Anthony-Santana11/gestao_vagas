package com.anthony.gestao_vagas.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.UUID;

public class TestUtils {
    public static String objectToJson(Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static String generateToken(UUID idCompany) {

        Algorithm algorithm = Algorithm.HMAC256("Anthony_0806ADMIN");
        var expiresIn = Instant.now().plus(Duration.ofHours(2));

        var token = JWT.create().withIssuer("gestao-vagas")
                .withClaim("roles", Arrays.asList("COMPANY"))
                .withExpiresAt(expiresIn)
                .withSubject(idCompany.toString())
                .sign(algorithm);
        return token;
    }
}
