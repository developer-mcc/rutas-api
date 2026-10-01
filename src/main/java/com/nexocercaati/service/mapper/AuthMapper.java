package com.nexocercaati.service.mapper;

import com.nexocercaati.model.Persona;
import com.nexocercaati.model.Usuario;
import com.nexocercaati.response.LoginResponse;
import java.time.Duration;
import org.springframework.stereotype.Component;

@Component
public class AuthMapper {

    private static final String TIPO_TOKEN = "Bearer";

    public LoginResponse aLoginResponse(Usuario usuario, String token, Duration expiracion) {
        return new LoginResponse(
                token,
                TIPO_TOKEN,
                expiracion.toSeconds(),
                usuario.getUsername(),
                nombreCompleto(usuario.getPersona()),
                usuario.getRol().name());
    }

    private String nombreCompleto(Persona persona) {
        return persona.getNombres() + " " + persona.getApellidos();
    }
}
