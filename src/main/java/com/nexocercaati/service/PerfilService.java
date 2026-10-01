package com.nexocercaati.service;

import com.nexocercaati.request.PerfilRequest;
import com.nexocercaati.response.PerfilResponse;

public interface PerfilService {

    /** Perfil completo del usuario con ese nombre de usuario (el del token ya validado). */
    PerfilResponse obtener(String username);

    /** Actualiza los datos personales del propio usuario y devuelve el perfil resultante. */
    PerfilResponse actualizar(String username, PerfilRequest request);
}
