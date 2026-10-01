package com.nexocercaati.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** Ajuste configurable del sitio: texto, imagen, color o enlace identificado por clave. */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ajuste_sitio")
public class AjusteSitio extends Auditable {

    @Id
    @Column(length = 120)
    private String clave;

    @Column(columnDefinition = "TEXT")
    private String valor;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TipoAjuste tipo;

    @Column(nullable = false, length = 40)
    private String grupo;

    @Column(nullable = false, length = 160)
    private String etiqueta;

    @Column(nullable = false)
    private int orden;
}
