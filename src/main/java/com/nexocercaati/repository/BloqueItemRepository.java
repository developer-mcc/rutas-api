package com.nexocercaati.repository;

import com.nexocercaati.model.BloqueItem;
import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BloqueItemRepository extends JpaRepository<BloqueItem, Long> {

    @EntityGraph(attributePaths = "seccion")
    List<BloqueItem> findByActivoTrueOrderBySeccionCodigoAscOrdenAsc();

    @EntityGraph(attributePaths = "seccion")
    List<BloqueItem> findBySeccionCodigoAndActivoTrueOrderByOrdenAsc(String seccionCodigo);

    @EntityGraph(attributePaths = "seccion")
    List<BloqueItem> findAllByOrderBySeccionCodigoAscOrdenAsc();

    @EntityGraph(attributePaths = "seccion")
    List<BloqueItem> findBySeccionCodigoOrderByOrdenAsc(String seccionCodigo);
}
