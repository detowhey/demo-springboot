package com.henriquealmeida.democrud.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.henriquealmeida.democrud.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secretKey;

    public String generateToken(User user) {
        try {
            return JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(user.getLogin())
                    .withExpiresAt(this.generateExpirationDate())
                    .sign(Algorithm.HMAC256(secretKey));
        } catch (JWTCreationException e) {
            throw new RuntimeException("Error while generating token", e);
        }
    }

    public String validateToken(String token) {
        try {
            return JWT.require(Algorithm.HMAC256(secretKey))
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            throw new JWTVerificationException("Error while verifying token", e);
        }
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(2L).toInstant(ZoneOffset.of("-03:00"));
    }
}
