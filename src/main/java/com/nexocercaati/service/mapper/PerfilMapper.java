package com.nexocercaati.service.mapper;

import com.nexocercaati.model.Persona;
import com.nexocercaati.model.Usuario;
import com.nexocercaati.request.PerfilRequest;
import com.nexocercaati.response.PerfilResponse;
import com.nexocercaati.response.PersonaResponse;
import java.util.Locale;
import org.springframework.stereotype.Component;

@Component
public class PerfilMapper {

    public PerfilResponse aPerfilResponse(Usuario usuario) {
        return new PerfilResponse(
                usuario.getId(),
                usuario.getUsername(),
                usuario.getRol().name(),
                usuario.isActivo(),
                usuario.getCreadoEn(),
                usuario.getActualizadoEn(),
                aPersonaResponse(usuario.getPersona()));
    }

    /** Copia los datos editables a la persona; los opcionales vacíos pasan a nulo y el correo a minúsculas. */
    public void aplicar(Persona persona, PerfilRequest request) {
        persona.setNombres(request.nombres().strip());
        persona.setApellidos(request.apellidos().strip());
        persona.setTipoDocumento(request.tipoDocumento());
        persona.setNumeroDocumento(Textos.vacioANulo(request.numeroDocumento()));
        String email = Textos.vacioANulo(request.email());
        persona.setEmail(email == null ? null : email.toLowerCase(Locale.ROOT));
        persona.setTelefono(Textos.vacioANulo(request.telefono()));
    }

    private PersonaResponse aPersonaResponse(Persona persona) {
        return new PersonaResponse(
                persona.getId(),
                persona.getNombres(),
                persona.getApellidos(),
                persona.getNombres() + " " + persona.getApellidos(),
                persona.getTipoDocumento() == null ? null : persona.getTipoDocumento().name(),
                persona.getNumeroDocumento(),
                persona.getEmail(),
                persona.getTelefono(),
                persona.getCreadoEn(),
                persona.getActualizadoEn());
    }
}
