package com.petcare.api_petcare.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mascotas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String especie;

    private String raza;

    private LocalDate fechaNacimiento;

    // Relación: Muchas mascotas pertenecen a un dueño
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dueno_id", nullable = false)
    @JsonIgnoreProperties("mascotas")
    private Dueno dueno;

    // Relación: Una mascota tiene muchas citas
    @OneToMany(mappedBy = "mascota", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("mascota")
    private List<Cita> citas = new ArrayList<>();
}