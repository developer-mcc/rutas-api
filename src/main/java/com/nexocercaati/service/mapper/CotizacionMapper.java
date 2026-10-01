package com.nexocercaati.service.mapper;

import com.nexocercaati.model.Cotizacion;
import com.nexocercaati.model.Tour;
import com.nexocercaati.request.CotizacionRequest;
import com.nexocercaati.response.CotizacionResponse;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class CotizacionMapper {

    public CotizacionResponse aResponse(Cotizacion cotizacion) {
        return new CotizacionResponse(
                cotizacion.getId(),
                Optional.ofNullable(cotizacion.getTour()).map(Tour::getId).orElse(null),
                cotizacion.getTourNombre(),
                cotizacion.getFechaViaje(),
                cotizacion.getPasajeros(),
                cotizacion.getRecojo(),
                cotizacion.isAtendida(),
                cotizacion.getCreadaEn());
    }

    public Cotizacion aEntidad(CotizacionRequest request, Tour tour) {
        var cotizacion = new Cotizacion();
        cotizacion.setTour(tour);
        cotizacion.setTourNombre(tour.getNombre());
        cotizacion.setFechaViaje(request.fechaViaje());
        cotizacion.setPasajeros(request.pasajeros());
        cotizacion.setRecojo(request.recojo().strip());
        return cotizacion;
    }
}
