package com.petcare.api_petcare.repository;

import com.petcare.api_petcare.entity.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {

    // Para el endpoint de relación: obtener todas las citas de una mascota específica
    List<Cita> findByMascotaId(Long mascotaId);
}