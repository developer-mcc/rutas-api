package com.nexocercaati.service.mapper;

import com.nexocercaati.model.Faq;
import com.nexocercaati.request.FaqRequest;
import com.nexocercaati.response.FaqResponse;
import org.springframework.stereotype.Component;

/** Mapper sin estado ni acceso a datos. Referencia del patrón para el resto de agregados. */
@Component
public class FaqMapper {

    public FaqResponse aResponse(Faq faq) {
        return new FaqResponse(
                faq.getId(), faq.getPregunta(), faq.getRespuesta(), faq.getOrden(), faq.isActivo());
    }

    public Faq aEntidad(FaqRequest request) {
        return actualizar(new Faq(), request);
    }

    /** Copia los datos del request sobre la entidad y la devuelve. */
    public Faq actualizar(Faq faq, FaqRequest request) {
        faq.setPregunta(request.pregunta());
        faq.setRespuesta(request.respuesta());
        faq.setOrden(request.orden());
        faq.setActivo(request.activo());
        return faq;
    }
}
