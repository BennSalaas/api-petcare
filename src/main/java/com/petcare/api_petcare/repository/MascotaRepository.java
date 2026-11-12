package com.petcare.api_petcare.repository;

import com.petcare.api_petcare.entity.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Long> {

    // Para el endpoint de relación: obtener todas las mascotas de un dueño específico
    List<Mascota> findByDuenoId(Long duenoId);
}