package com.nexocercaati.service.validacion;

import com.nexocercaati.model.TipoAjuste;
import com.nexocercaati.request.ValidacionPatrones;
import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import org.springframework.stereotype.Component;

/** Valida el valor de un ajuste según el tipo de control con el que se edita. */
@Component
public class ValorAjusteValidador {

    private static final int MAXIMO_TEXTO = 300;
    private static final int MAXIMO_TEXTO_LARGO = 4000;
    private static final Pattern COLOR = Pattern.compile("^#[0-9a-fA-F]{6}$");
    /** Vacío, http(s), wa.me, ancla o ruta relativa (nunca javascript: ni //host). */
    private static final Pattern ENLACE = Pattern.compile("^(|https?://\\S+|wa\\.me/\\S+|#\\S*|/(?!/)\\S*)$");
    private static final Pattern IMAGEN = Pattern.compile(ValidacionPatrones.IMAGEN_URL);

    private final Map<TipoAjuste, Predicate<String>> reglas = new EnumMap<>(Map.of(
            TipoAjuste.TEXTO, valor -> valor.length() <= MAXIMO_TEXTO,
            TipoAjuste.TEXTO_LARGO, valor -> valor.length() <= MAXIMO_TEXTO_LARGO,
            TipoAjuste.COLOR, COLOR.asMatchPredicate(),
            TipoAjuste.ENLACE, ENLACE.asMatchPredicate(),
            TipoAjuste.IMAGEN, IMAGEN.asMatchPredicate()));

    public boolean esValido(TipoAjuste tipo, String valor) {
        return reglas.get(tipo).test(Optional.ofNullable(valor).orElse(""));
    }
}
