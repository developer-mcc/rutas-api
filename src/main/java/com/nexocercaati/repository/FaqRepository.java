package com.nexocercaati.repository;

import com.nexocercaati.model.Faq;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaqRepository extends JpaRepository<Faq, Long> {

    List<Faq> findByActivoTrueOrderByOrdenAsc();

    List<Faq> findAllByOrderByOrdenAsc();
}
