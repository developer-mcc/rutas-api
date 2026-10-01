package com.nexocercaati.service;

import com.nexocercaati.request.AtendidaRequest;
import com.nexocercaati.response.CotizacionResponse;
import java.util.List;

public interface CotizacionAdminService {

    List<CotizacionResponse> listar();

    CotizacionResponse marcarAtendida(Long id, AtendidaRequest request);
}
