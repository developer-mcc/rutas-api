package com.nexocercaati.service.mapper;

import com.nexocercaati.model.Tour;
import com.nexocercaati.model.TourInclusion;
import com.nexocercaati.request.TourRequest;
import com.nexocercaati.response.TourResponse;
import java.util.List;
import java.util.stream.IntStream;
import org.springframework.stereotype.Component;

@Component
public class TourMapper {

    public TourResponse aResponse(Tour tour) {
        List<String> inclusiones = tour.getInclusiones().stream()
                .map(TourInclusion::getTexto)
                .toList();
        return new TourResponse(
                tour.getId(), tour.getSlug(), tour.getNombre(), tour.getEmoji(), tour.getEtiqueta(),
                tour.getInsignia(), tour.getDescripcion(), tour.getResumen(), tour.getPrecioDesde(),
                tour.getMoneda(), tour.getTextoPrecio(), tour.getUnidadPrecio(),
                tour.getTextoPorPersona(), tour.getImagenUrl(), tour.isDestacado(), tour.getOrden(),
                tour.isActivo(), inclusiones);
    }

    public Tour aEntidad(TourRequest request) {
        return actualizar(new Tour(), request);
    }

    /** Copia los datos del request sobre el tour y reemplaza sus inclusiones respetando el orden. */
    public Tour actualizar(Tour tour, TourRequest request) {
        tour.setSlug(request.slug());
        tour.setNombre(request.nombre());
        tour.setEmoji(Textos.vacioANulo(request.emoji()));
        tour.setEtiqueta(Textos.vacioANulo(request.etiqueta()));
        tour.setInsignia(Textos.vacioANulo(request.insignia()));
        tour.setDescripcion(request.descripcion());
        tour.setResumen(Textos.vacioANulo(request.resumen()));
        tour.setPrecioDesde(request.precioDesde());
        tour.setMoneda(request.moneda());
        tour.setTextoPrecio(Textos.vacioANulo(request.textoPrecio()));
        tour.setUnidadPrecio(Textos.vacioANulo(request.unidadPrecio()));
        tour.setTextoPorPersona(Textos.vacioANulo(request.textoPorPersona()));
        tour.setImagenUrl(Textos.vacioANulo(request.imagenUrl()));
        tour.setDestacado(request.destacado());
        tour.setOrden(request.orden());
        tour.setActivo(request.activo());
        reemplazarInclusiones(tour, request.inclusiones());
        return tour;
    }

    private void reemplazarInclusiones(Tour tour, List<String> textos) {
        tour.getInclusiones().clear();
        IntStream.range(0, textos.size())
                .mapToObj(indice -> aInclusion(tour, textos.get(indice), indice))
                .forEach(tour.getInclusiones()::add);
    }

    private TourInclusion aInclusion(Tour tour, String texto, int orden) {
        var inclusion = new TourInclusion();
        inclusion.setTour(tour);
        inclusion.setTexto(texto);
        inclusion.setOrden(orden);
        return inclusion;
    }
}
