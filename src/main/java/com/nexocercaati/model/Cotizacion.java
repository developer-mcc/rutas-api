package com.nexocercaati.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.Instant;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "cotizacion")
@EntityListeners(AuditingEntityListener.class)
public class Cotizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Puede quedar nulo si el tour se elimina; por eso se conserva tourNombre. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tour_id")
    private Tour tour;

    @Column(name = "tour_nombre", nullable = false, length = 160)
    private String tourNombre;

    @Column(name = "fecha_viaje", nullable = false)
    private LocalDate fechaViaje;

    /** "1".."4" o "5+". */
    @Column(nullable = false, length = 10)
    private String pasajeros;

    @Column(nullable = false, length = 200)
    private String recojo;

    @Column(nullable = false)
    private boolean atendida;

    @CreatedDate
    @Column(name = "creada_en", nullable = false, updatable = false)
    private Instant creadaEn;
}
