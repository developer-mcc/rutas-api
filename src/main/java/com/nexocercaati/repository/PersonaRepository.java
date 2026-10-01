package com.nexocercaati.repository;

import com.nexocercaati.model.Persona;
import com.nexocercaati.model.TipoDocumento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Long> {

    boolean existsByEmailIgnoreCaseAndIdNot(String email, Long id);

    boolean existsByTipoDocumentoAndNumeroDocumentoAndIdNot(
            TipoDocumento tipoDocumento, String numeroDocumento, Long id);
}
