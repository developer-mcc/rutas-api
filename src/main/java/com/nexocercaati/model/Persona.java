package com.nexocercaati.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** Datos personales. No guarda credenciales: eso es responsabilidad de {@link Usuario}. */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "persona")
public class Persona extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String nombres;

    @Column(nullable = false, length = 120)
    private String apellidos;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_documento", length = 15)
    private TipoDocumento tipoDocumento;

    @Column(name = "numero_documento", length = 20)
    private String numeroDocumento;

    @Column(unique = true, length = 160)
    private String email;

    @Column(length = 30)
    private String telefono;
}
