package com.petcare.api_petcare.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "duenos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Dueno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String rut;

    @Column(nullable = false)
    private String nombre;

    private String telefono;

    @Column(nullable = false)
    private String email;

    // Relación: Un dueño tiene muchas mascotas
    @OneToMany(mappedBy = "dueno", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("dueno") // Evita recursión infinita en el JSON
    private List<Mascota> mascotas = new ArrayList<>();
}