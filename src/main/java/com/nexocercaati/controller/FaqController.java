package com.nexocercaati.controller;

import com.nexocercaati.response.FaqResponse;
import com.nexocercaati.service.FaqConsultaService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/faqs")
@RequiredArgsConstructor
public class FaqController {

    private final FaqConsultaService faqConsultaService;

    @GetMapping
    public List<FaqResponse> listar() {
        return faqConsultaService.listarActivas();
    }
}
