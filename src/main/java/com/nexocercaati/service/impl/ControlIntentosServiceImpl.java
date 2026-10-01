package com.nexocercaati.service.impl;

import com.nexocercaati.service.ControlIntentosService;
import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;

/** Bloquea un usuario 15 minutos tras 5 fallos seguidos. Estado en memoria (una sola instancia). */
@Service
public class ControlIntentosServiceImpl implements ControlIntentosService {

    private static final int MAX_FALLOS = 5;
    private static final Duration DURACION_BLOQUEO = Duration.ofMinutes(15);
    private static final int MAX_REGISTROS = 10_000;

    private record Registro(int fallos, Instant bloqueadoHasta) {

        boolean bloqueado(Instant ahora) {
            return bloqueadoHasta != null && ahora.isBefore(bloqueadoHasta);
        }

        boolean expirado(Instant ahora) {
            return bloqueadoHasta != null && !ahora.isBefore(bloqueadoHasta);
        }

        Registro conFallo(Instant ahora) {
            int total = (expirado(ahora) ? 0 : fallos) + 1;
            return new Registro(total, total >= MAX_FALLOS ? ahora.plus(DURACION_BLOQUEO) : null);
        }
    }

    private final Map<String, Registro> registros = new ConcurrentHashMap<>();

    @Override
    public boolean estaBloqueado(String username) {
        Instant ahora = Instant.now();
        return Optional.ofNullable(registros.get(username))
                .map(registro -> registro.bloqueado(ahora))
                .orElse(false);
    }

    @Override
    public void registrarFallo(String username) {
        Instant ahora = Instant.now();
        purgarSiHaceFalta(ahora);
        registros.merge(username, new Registro(0, null).conFallo(ahora),
                (actual, nuevo) -> actual.conFallo(ahora));
    }

    @Override
    public void limpiar(String username) {
        registros.remove(username);
    }

    /** Evita que usuarios inventados llenen la memoria. */
    private void purgarSiHaceFalta(Instant ahora) {
        if (registros.size() >= MAX_REGISTROS) {
            registros.values().removeIf(registro -> registro.expirado(ahora) || !registro.bloqueado(ahora));
        }
    }
}
