package com.ecommerce.usuarios.api.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ecommerce.usuarios.api.model.Cliente;

@Service
public class TokenService {
    // Gera o token a partir do cliente/usuário
    public String generateToken(Cliente cliente) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

        return JWT.create()
                .withIssuer(TOKEN_ISSUER)
                .withSubject(cliente.getCpf())
                .withExpiresAt(_expirationDate())
                .withClaim("id", cliente.getId())
                .sign(algorithm);
    }

    // Obtém o sujeito (cliente/usuário) a partir do token 
    public String getSubject(String token) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

        return JWT.require(algorithm)
                .withIssuer(TOKEN_ISSUER)
                .build()
                .verify(token)
                .getSubject();
    }

    // Gera a data de expiração do token JWT
    private Instant _expirationDate() {
        return LocalDateTime.now().plusDays(30).toInstant(ZoneOffset.of("-03:00"));
    }

    @Value("${spring.security.secret-key}")
    private String SECRET_KEY;

    @Value("${spring.security.token-issuer}")
    private String TOKEN_ISSUER;

}
