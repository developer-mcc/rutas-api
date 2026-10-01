package com.nexocercaati.repository;

import com.nexocercaati.model.AjusteSitio;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AjusteSitioRepository extends JpaRepository<AjusteSitio, String> {

    List<AjusteSitio> findAllByOrderByGrupoAscOrdenAsc();
}
