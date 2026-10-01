package com.nexocercaati.service;

import com.nexocercaati.request.AjustesRequest;
import com.nexocercaati.response.AjusteResponse;
import java.util.List;

public interface AjusteAdminService {

    List<AjusteResponse> listar();

    /** Actualiza solo el valor de ajustes existentes, validándolo según su tipo. */
    List<AjusteResponse> actualizar(AjustesRequest request);
}
