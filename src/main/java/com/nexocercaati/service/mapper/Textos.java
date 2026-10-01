package com.nexocercaati.service.mapper;

import org.springframework.util.StringUtils;

/** Utilidades de texto compartidas por los mappers. */
final class Textos {

    private Textos() {
    }

    /** Los campos opcionales vacíos se guardan como nulo. */
    static String vacioANulo(String valor) {
        return StringUtils.hasText(valor) ? valor.strip() : null;
    }
}
