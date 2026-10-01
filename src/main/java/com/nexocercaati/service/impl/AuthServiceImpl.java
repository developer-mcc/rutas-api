package com.nexocercaati.service.impl;

import com.nexocercaati.config.AppProperties;
import com.nexocercaati.exception.CredencialesInvalidasException;
import com.nexocercaati.exception.CuentaBloqueadaException;
import com.nexocercaati.model.Usuario;
import com.nexocercaati.repository.UsuarioRepository;
import com.nexocercaati.request.LoginRequest;
import com.nexocercaati.response.LoginResponse;
import com.nexocercaati.service.AuthService;
import com.nexocercaati.service.ControlIntentosService;
import com.nexocercaati.service.TokenService;
import com.nexocercaati.service.mapper.AuthMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository usuarioRepository;
    private final TokenService tokenService;
    private final ControlIntentosService controlIntentos;
    private final AuthMapper authMapper;
    private final AppProperties properties;

    @Override
    @Transactional(readOnly = true)
    public LoginResponse login(LoginRequest request) {
        String username = request.username();
        if (controlIntentos.estaBloqueado(username)) {
            throw new CuentaBloqueadaException();
        }
        autenticar(username, request.password());
        controlIntentos.limpiar(username);

        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(CredencialesInvalidasException::new);
        String token = tokenService.generar(usuario.getUsername(), usuario.getRol());
        return authMapper.aLoginResponse(usuario, token, properties.jwt().expiracion());
    }

    /** Cualquier fallo se responde igual para no revelar si el usuario existe. */
    private void autenticar(String username, String password) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
        } catch (AuthenticationException ex) {
            controlIntentos.registrarFallo(username);
            throw new CredencialesInvalidasException();
        }
    }
}
