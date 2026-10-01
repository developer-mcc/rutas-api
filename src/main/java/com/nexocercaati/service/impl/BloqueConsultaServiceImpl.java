package com.nexocercaati.service.impl;

import com.nexocercaati.model.BloqueItem;
import com.nexocercaati.repository.BloqueItemRepository;
import com.nexocercaati.response.BloqueResponse;
import com.nexocercaati.service.BloqueConsultaService;
import com.nexocercaati.service.mapper.BloqueMapper;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class BloqueConsultaServiceImpl implements BloqueConsultaService {

    private final BloqueItemRepository bloqueRepository;
    private final BloqueMapper bloqueMapper;

    @Override
    @Transactional(readOnly = true)
    public List<BloqueResponse> listarActivos(String seccionCodigo) {
        List<BloqueItem> bloques = Optional.ofNullable(seccionCodigo)
                .filter(StringUtils::hasText)
                .map(bloqueRepository::findBySeccionCodigoAndActivoTrueOrderByOrdenAsc)
                .orElseGet(bloqueRepository::findByActivoTrueOrderBySeccionCodigoAscOrdenAsc);
        return bloques.stream().map(bloqueMapper::aResponse).toList();
    }
}
