package com.nexocercaati.config;

import java.time.Duration;
import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;

/** Propiedades de la aplicación (prefijo {@code app}), enlazadas desde application.yml. */
@ConfigurationProperties(prefix = "app")
public record AppProperties(
        String uploadsDir,
        boolean serveUploads,
        List<String> corsOrigins,
        Jwt jwt) {

    public record Jwt(String secret, Duration expiracion) {
    }
}
