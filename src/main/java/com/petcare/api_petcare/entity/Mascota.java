package com.petcare.api_petcare.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(nullable = false)
    private String nombre;

    @NotBlank(message = "La especie es obligatoria")
    @Column(nullable = false)
    private String especie;

    private String raza;

    private LocalDate fechaNacimiento;

    // Relación: Muchas mascotas pertenecen a un dueño
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dueno_id", nullable = false)
    @JsonIgnoreProperties("mascotas")
    private Dueno dueno;

    // Relación: Una mascota tiene muchas citas (sin cascade, para no borrar el historial en silencio)
    @OneToMany(mappedBy = "mascota", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("mascota")
    private List<Cita> citas = new ArrayList<>();
}