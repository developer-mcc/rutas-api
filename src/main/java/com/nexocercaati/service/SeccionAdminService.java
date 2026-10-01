package com.nexocercaati.service;

import com.nexocercaati.request.SeccionRequest;
import com.nexocercaati.response.SeccionResponse;
import java.util.List;

public interface SeccionAdminService {

    List<SeccionResponse> listar();

    SeccionResponse actualizar(String codigo, SeccionRequest request);
}
