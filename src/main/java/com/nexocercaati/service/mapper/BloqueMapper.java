package com.nexocercaati.service.mapper;

import com.nexocercaati.model.BloqueItem;
import com.nexocercaati.model.Seccion;
import com.nexocercaati.request.BloqueRequest;
import com.nexocercaati.response.BloqueResponse;
import org.springframework.stereotype.Component;

@Component
public class BloqueMapper {

    public BloqueResponse aResponse(BloqueItem bloque) {
        return new BloqueResponse(
                bloque.getId(),
                bloque.getSeccion().getCodigo(),
                bloque.getTitulo(),
                bloque.getTexto(),
                bloque.getIcono(),
                bloque.getImagenUrl(),
                bloque.getOrden(),
                bloque.isActivo());
    }

    public BloqueItem aEntidad(BloqueRequest request, Seccion seccion) {
        return actualizar(new BloqueItem(), request, seccion);
    }

    public BloqueItem actualizar(BloqueItem bloque, BloqueRequest request, Seccion seccion) {
        bloque.setSeccion(seccion);
        bloque.setTitulo(request.titulo());
        bloque.setTexto(Textos.vacioANulo(request.texto()));
        bloque.setIcono(Textos.vacioANulo(request.icono()));
        bloque.setImagenUrl(Textos.vacioANulo(request.imagenUrl()));
        bloque.setOrden(request.orden());
        bloque.setActivo(request.activo());
        return bloque;
    }
}
