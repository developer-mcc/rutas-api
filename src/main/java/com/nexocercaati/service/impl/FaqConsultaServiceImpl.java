package com.nexocercaati.service.impl;

import com.nexocercaati.repository.FaqRepository;
import com.nexocercaati.response.FaqResponse;
import com.nexocercaati.service.FaqConsultaService;
import com.nexocercaati.service.mapper.FaqMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FaqConsultaServiceImpl implements FaqConsultaService {

    private final FaqRepository faqRepository;
    private final FaqMapper faqMapper;

    @Override
    @Transactional(readOnly = true)
    public List<FaqResponse> listarActivas() {
        return faqRepository.findByActivoTrueOrderByOrdenAsc().stream()
                .map(faqMapper::aResponse)
                .toList();
    }
}
