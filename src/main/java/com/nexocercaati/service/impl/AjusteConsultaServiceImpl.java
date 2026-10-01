package com.nexocercaati.service.impl;

import com.nexocercaati.repository.AjusteSitioRepository;
import com.nexocercaati.service.AjusteConsultaService;
import com.nexocercaati.service.mapper.AjusteMapper;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AjusteConsultaServiceImpl implements AjusteConsultaService {

    private final AjusteSitioRepository ajusteRepository;
    private final AjusteMapper ajusteMapper;

    @Override
    @Transactional(readOnly = true)
    public Map<String, String> obtenerMapa() {
        return ajusteMapper.aMapa(ajusteRepository.findAllByOrderByGrupoAscOrdenAsc());
    }
}
