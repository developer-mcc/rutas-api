package com.nexocercaati.service.impl;

import com.nexocercaati.exception.RecursoDuplicadoException;
import com.nexocercaati.exception.RecursoNoEncontradoException;
import com.nexocercaati.exception.SolicitudInvalidaException;
import com.nexocercaati.model.Persona;
import com.nexocercaati.model.TipoDocumento;
import com.nexocercaati.model.Usuario;
import com.nexocercaati.repository.PersonaRepository;
import com.nexocercaati.repository.UsuarioRepository;
import com.nexocercaati.request.PerfilRequest;
import com.nexocercaati.response.PerfilResponse;
import com.nexocercaati.service.PerfilService;
import com.nexocercaati.service.mapper.PerfilMapper;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
public class PerfilServiceImpl implements PerfilService {

    private static final String RECURSO = "Usuario";
    private static final Pattern DNI = Pattern.compile("^\\d{8}$");
    private static final Pattern RUC = Pattern.compile("^\\d{11}$");

    private final UsuarioRepository usuarioRepository;
    private final PersonaRepository personaRepository;
    private final PerfilMapper perfilMapper;

    @Override
    @Transactional(readOnly = true)
    public PerfilResponse obtener(String username) {
        return perfilMapper.aPerfilResponse(buscar(username));
    }

    @Override
    @Transactional
    public PerfilResponse actualizar(String username, PerfilRequest request) {
        Usuario usuario = buscar(username);
        Persona persona = usuario.getPersona();
        validarDocumento(request);
        validarUnicidad(persona, request);
        perfilMapper.aplicar(persona, request);
        return perfilMapper.aPerfilResponse(usuario);
    }

    private Usuario buscar(String username) {
        return usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RecursoNoEncontradoException(RECURSO, username));
    }

    /** El tipo y el número van juntos, y el número debe tener el formato de su tipo. */
    private void validarDocumento(PerfilRequest request) {
        boolean hayNumero = StringUtils.hasText(request.numeroDocumento());
        TipoDocumento tipo = request.tipoDocumento();
        if (hayNumero != (tipo != null)) {
            throw new SolicitudInvalidaException("Indica el tipo y el número de documento, o deja ambos vacíos");
        }
        if (!hayNumero) {
            return;
        }
        String numero = request.numeroDocumento().strip();
        if (tipo == TipoDocumento.DNI && !DNI.matcher(numero).matches()) {
            throw new SolicitudInvalidaException("El DNI debe tener 8 dígitos");
        }
        if (tipo == TipoDocumento.RUC && !RUC.matcher(numero).matches()) {
            throw new SolicitudInvalidaException("El RUC debe tener 11 dígitos");
        }
    }

    private void validarUnicidad(Persona persona, PerfilRequest request) {
        if (StringUtils.hasText(request.email())
                && personaRepository.existsByEmailIgnoreCaseAndIdNot(request.email().strip(), persona.getId())) {
            throw new RecursoDuplicadoException("Ya existe otra cuenta con ese correo electrónico");
        }
        if (StringUtils.hasText(request.numeroDocumento())
                && personaRepository.existsByTipoDocumentoAndNumeroDocumentoAndIdNot(
                        request.tipoDocumento(), request.numeroDocumento().strip(), persona.getId())) {
            throw new RecursoDuplicadoException("Ya existe otra persona con ese documento");
        }
    }
}
