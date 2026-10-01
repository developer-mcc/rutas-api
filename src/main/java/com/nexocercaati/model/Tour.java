package com.nexocercaati.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tour")
public class Tour extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 120)
    private String slug;

    @Column(nullable = false, length = 160)
    private String nombre;

    @Column(length = 16)
    private String emoji;

    @Column(length = 120)
    private String etiqueta;

    @Column(length = 60)
    private String insignia;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    @Column(length = 200)
    private String resumen;

    @Column(name = "precio_desde", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioDesde;

    @Column(nullable = false, length = 3)
    private String moneda = "PEN";

    @Column(name = "texto_precio", length = 120)
    private String textoPrecio;

    @Column(name = "unidad_precio", length = 120)
    private String unidadPrecio;

    @Column(name = "texto_por_persona", length = 120)
    private String textoPorPersona;

    @Column(name = "imagen_url", length = 300)
    private String imagenUrl;

    @Column(nullable = false)
    private boolean destacado;

    @Column(nullable = false)
    private int orden;

    @Column(nullable = false)
    private boolean activo = true;

    @OneToMany(mappedBy = "tour", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("orden ASC")
    private List<TourInclusion> inclusiones = new ArrayList<>();
}
