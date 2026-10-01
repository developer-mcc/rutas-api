package com.nexocercaati.service.impl;

import com.nexocercaati.repository.SeccionRepository;
import com.nexocercaati.response.SeccionResponse;
import com.nexocercaati.service.SeccionConsultaService;
import com.nexocercaati.service.mapper.SeccionMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SeccionConsultaServiceImpl implements SeccionConsultaService {

    private final SeccionRepository seccionRepository;
    private final SeccionMapper seccionMapper;

    @Override
    @Transactional(readOnly = true)
    public List<SeccionResponse> listarVisibles() {
        return seccionRepository.findByVisibleTrueOrderByOrdenAsc().stream()
                .map(seccionMapper::aResponse)
                .toList();
    }
}
