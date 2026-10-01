package com.nexocercaati.config;

import java.nio.file.Path;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Solo desarrollo: sirve /uploads/ desde Spring Boot. En producción lo sirve Nginx
 * directamente desde disco (app.serve-uploads=false).
 */
@Configuration
@RequiredArgsConstructor
@ConditionalOnProperty(name = "app.serve-uploads", havingValue = "true")
public class WebConfig implements WebMvcConfigurer {

    private final AppProperties properties;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String ubicacion = Path.of(properties.uploadsDir()).toAbsolutePath().toUri().toString();
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations(ubicacion.endsWith("/") ? ubicacion : ubicacion + "/");
    }
}
