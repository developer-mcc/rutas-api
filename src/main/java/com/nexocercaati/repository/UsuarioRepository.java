package com.nexocercaati.repository;

import com.nexocercaati.model.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @EntityGraph(attributePaths = "persona")
    Optional<Usuario> findByUsername(String username);
}
