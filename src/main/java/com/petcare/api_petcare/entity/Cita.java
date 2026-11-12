package com.petcare.api_petcare.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "citas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @Column(nullable = false)
    private String motivo;

    private String diagnostico;

    @Column(nullable = false)
    private Double costo;

    // Relación: Muchas citas pertenecen a una mascota
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mascota_id", nullable = false)
    @JsonIgnoreProperties("citas")
    private Mascota mascota;
}