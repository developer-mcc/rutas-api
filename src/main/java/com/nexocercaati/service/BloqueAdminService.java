package com.nexocercaati.service;

import com.nexocercaati.request.BloqueRequest;
import com.nexocercaati.request.OrdenRequest;
import com.nexocercaati.response.BloqueResponse;
import java.util.List;

public interface BloqueAdminService {

    /** Con un código de sección filtra por ella; vacío o nulo, devuelve todos. */
    List<BloqueResponse> listar(String seccionCodigo);

    BloqueResponse obtener(Long id);

    BloqueResponse crear(BloqueRequest request);

    BloqueResponse actualizar(Long id, BloqueRequest request);

    void eliminar(Long id);

    /** La posición de cada id en la lista pasa a ser su nuevo orden. */
    void reordenar(OrdenRequest request);
}
