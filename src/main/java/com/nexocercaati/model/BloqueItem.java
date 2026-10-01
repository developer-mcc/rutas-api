package com.nexocercaati.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** Elemento de una lista corta de una sección (valores, confort, confianza, etc.). */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "bloque_item")
public class BloqueItem extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "seccion_codigo")
    private Seccion seccion;

    @Column(nullable = false, length = 200)
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String texto;

    @Column(length = 40)
    private String icono;

    @Column(name = "imagen_url", length = 300)
    private String imagenUrl;

    @Column(nullable = false)
    private int orden;

    @Column(nullable = false)
    private boolean activo = true;
}
