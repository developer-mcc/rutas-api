package com.nexocercaati.service.mapper;

import com.nexocercaati.model.Seccion;
import com.nexocercaati.request.SeccionRequest;
import com.nexocercaati.response.SeccionResponse;
import org.springframework.stereotype.Component;

@Component
public class SeccionMapper {

    public SeccionResponse aResponse(Seccion seccion) {
        return new SeccionResponse(
                seccion.getCodigo(),
                seccion.getNombre(),
                seccion.getTipo().name(),
                seccion.isVisible(),
                seccion.getOrden());
    }

    /** El código y el tipo no se editan: cada sección tiene su componente en el frontend. */
    public Seccion actualizar(Seccion seccion, SeccionRequest request) {
        seccion.setNombre(request.nombre());
        seccion.setVisible(request.visible());
        seccion.setOrden(request.orden());
        return seccion;
    }
}
