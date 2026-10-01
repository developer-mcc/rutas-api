package com.nexocercaati.config;

import com.nexocercaati.exception.ConfiguracionInvalidaException;
import org.springframework.boot.diagnostics.AbstractFailureAnalyzer;
import org.springframework.boot.diagnostics.FailureAnalysis;

/** Muestra un error de configuración como un aviso corto (problema + qué hacer) en vez de una pila de 100 líneas. */
public class ConfiguracionInvalidaFailureAnalyzer
        extends AbstractFailureAnalyzer<ConfiguracionInvalidaException> {

    @Override
    protected FailureAnalysis analyze(Throwable raiz, ConfiguracionInvalidaException causa) {
        return new FailureAnalysis(causa.getMessage(), causa.getAccion(), causa);
    }
}
