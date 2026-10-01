package com.nexocercaati.repository;

import com.nexocercaati.model.Cotizacion;
import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CotizacionRepository extends JpaRepository<Cotizacion, Long> {

    @EntityGraph(attributePaths = "tour")
    List<Cotizacion> findAllByOrderByCreadaEnDesc();

    boolean existsByTourId(Long tourId);
}
