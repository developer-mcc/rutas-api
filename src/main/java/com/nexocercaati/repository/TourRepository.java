package com.nexocercaati.repository;

import com.nexocercaati.model.Tour;
import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourRepository extends JpaRepository<Tour, Long> {

    @EntityGraph(attributePaths = "inclusiones")
    List<Tour> findByActivoTrueOrderByOrdenAsc();

    @EntityGraph(attributePaths = "inclusiones")
    List<Tour> findAllByOrderByOrdenAsc();

    boolean existsBySlug(String slug);

    boolean existsBySlugAndIdNot(String slug, Long id);
}
