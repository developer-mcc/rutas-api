package com.nexocercaati.service;

import com.nexocercaati.request.CambioPasswordRequest;

public interface CuentaService {

    void cambiarPassword(String username, CambioPasswordRequest request);
}
