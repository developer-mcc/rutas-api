package com.nexocercaati.security;

import com.nexocercaati.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/** Autentica cada petición a partir del token Bearer, si viene y es válido. */
@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private static final String PREFIJO_BEARER = "Bearer ";
    private static final String PREFIJO_ROL = "ROLE_";

    private final TokenService tokenService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        extraerToken(request)
                .flatMap(tokenService::validar)
                .map(this::aAutenticacion)
                .ifPresent(SecurityContextHolder.getContext()::setAuthentication);
        chain.doFilter(request, response);
    }

    private Optional<String> extraerToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(HttpHeaders.AUTHORIZATION))
                .filter(cabecera -> cabecera.startsWith(PREFIJO_BEARER))
                .map(cabecera -> cabecera.substring(PREFIJO_BEARER.length()));
    }

    private Authentication aAutenticacion(TokenClaims claims) {
        var autoridades = List.of(new SimpleGrantedAuthority(PREFIJO_ROL + claims.rol()));
        return UsernamePasswordAuthenticationToken.authenticated(
                claims.username(), null, autoridades);
    }
}
