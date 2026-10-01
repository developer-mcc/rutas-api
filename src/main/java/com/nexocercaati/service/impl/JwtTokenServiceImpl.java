package com.nexocercaati.service.impl;

import com.nexocercaati.config.AppProperties;
import com.nexocercaati.exception.ConfiguracionInvalidaException;
import com.nexocercaati.model.Rol;
import com.nexocercaati.security.TokenClaims;
import com.nexocercaati.service.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import javax.crypto.SecretKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JwtTokenServiceImpl implements TokenService {

    private static final int SECRETO_MINIMO_BYTES = 32;
    private static final String CLAIM_ROL = "rol";

    private final SecretKey clave;
    private final Duration expiracion;

    public JwtTokenServiceImpl(AppProperties properties) {
        byte[] secreto = properties.jwt().secret().getBytes(StandardCharsets.UTF_8);
        if (secreto.length < SECRETO_MINIMO_BYTES) {
            throw new ConfiguracionInvalidaException(
                    "La variable JWT_SECRET no está definida o tiene menos de 32 caracteres.",
                    """
                    Defínela con un texto de al menos 32 caracteres (por ejemplo: openssl rand -base64 48).
                      - IntelliJ: Run > Edit Configurations > Environment variables > JWT_SECRET=...
                      - PowerShell: $env:JWT_SECRET = "..."; .\\gradlew.bat bootRun
                      - Docker: variable JWT_SECRET en el .env (ver README).""");
        }
        this.clave = Keys.hmacShaKeyFor(secreto);
        this.expiracion = properties.jwt().expiracion();
    }

    @Override
    public String generar(String username, Rol rol) {
        Instant ahora = Instant.now();
        return Jwts.builder()
                .subject(username)
                .claim(CLAIM_ROL, rol.name())
                .issuedAt(Date.from(ahora))
                .expiration(Date.from(ahora.plus(expiracion)))
                .signWith(clave)
                .compact();
    }

    @Override
    public Optional<TokenClaims> validar(String token) {
        try {
            Claims claims = Jwts.parser().verifyWith(clave).build()
                    .parseSignedClaims(token).getPayload();
            return Optional.of(new TokenClaims(claims.getSubject(), claims.get(CLAIM_ROL, String.class)));
        } catch (JwtException | IllegalArgumentException ex) {
            log.debug("Token rechazado: {}", ex.getClass().getSimpleName());
            return Optional.empty();
        }
    }
}
