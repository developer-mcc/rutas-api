package com.nexocercaati.service.mapper;

import com.nexocercaati.model.AjusteSitio;
import com.nexocercaati.response.AjusteResponse;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class AjusteMapper {

    public AjusteResponse aResponse(AjusteSitio ajuste) {
        return new AjusteResponse(
                ajuste.getClave(),
                ajuste.getValor(),
                ajuste.getTipo().name(),
                ajuste.getGrupo(),
                ajuste.getEtiqueta(),
                ajuste.getOrden());
    }

    /** Mapa clave → valor para la web pública; un valor nulo se entrega como texto vacío. */
    public Map<String, String> aMapa(List<AjusteSitio> ajustes) {
        return ajustes.stream().collect(Collectors.toMap(
                AjusteSitio::getClave,
                ajuste -> Optional.ofNullable(ajuste.getValor()).orElse(""),
                (primero, segundo) -> primero,
                LinkedHashMap::new));
    }

    public AjusteSitio actualizarValor(AjusteSitio ajuste, String valor) {
        ajuste.setValor(valor);
        return ajuste;
    }
}
