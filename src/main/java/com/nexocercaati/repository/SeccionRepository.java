package com.nexocercaati.repository;

import com.nexocercaati.model.Seccion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeccionRepository extends JpaRepository<Seccion, String> {

    List<Seccion> findByVisibleTrueOrderByOrdenAsc();

    List<Seccion> findAllByOrderByOrdenAsc();
}
