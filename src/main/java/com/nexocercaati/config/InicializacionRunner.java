package com.nexocercaati.config;

import com.nexocercaati.service.InicializacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/** Dispara los datos iniciales al arrancar; la lógica vive en {@link InicializacionService}. */
@Component
@RequiredArgsConstructor
public class InicializacionRunner implements ApplicationRunner {

    private final InicializacionService inicializacionService;

    @Override
    public void run(ApplicationArguments args) {
        inicializacionService.copiarImagenesSemilla();
    }
}
