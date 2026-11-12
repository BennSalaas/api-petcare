package com.petcare.api_petcare.repository;

import com.petcare.api_petcare.entity.Dueno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DuenoRepository extends JpaRepository<Dueno, Long> {

    // Método para validar que no existan RUTs duplicados
    boolean existsByRut(String rut);

    // Buscar dueño por RUT
    Optional<Dueno> findByRut(String rut);
}