package com.nexocercaati.service;

import com.nexocercaati.request.CotizacionRequest;
import com.nexocercaati.response.CotizacionResponse;

/** Registro público de solicitudes de cotización. */
public interface CotizacionService {

    CotizacionResponse registrar(CotizacionRequest request);
}
