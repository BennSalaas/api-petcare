package com.petcare.api_petcare.service;

import com.petcare.api_petcare.entity.Cita;
import com.petcare.api_petcare.entity.Mascota;
import com.petcare.api_petcare.repository.CitaRepository;
import com.petcare.api_petcare.repository.MascotaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CitaService {

    private final CitaRepository citaRepository;
    private final MascotaRepository mascotaRepository;

    public CitaService(CitaRepository citaRepository, MascotaRepository mascotaRepository) {
        this.citaRepository = citaRepository;
        this.mascotaRepository = mascotaRepository;
    }

    public List<Cita> obtenerTodas() {
        return citaRepository.findAll();
    }

    public Cita obtenerPorId(Long id) {
        return citaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cita no encontrada con ID: " + id));
    }

    public List<Cita> obtenerPorMascotaId(Long mascotaId) {
        // Valida que la mascota exista antes de traer sus citas
        if (!mascotaRepository.existsById(mascotaId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Mascota no encontrada con ID: " + mascotaId);
        }
        return citaRepository.findByMascotaId(mascotaId);
    }

    // Validación clave del enunciado: No crear cita para una mascota inexistente
    public Cita guardar(Cita cita, Long mascotaId) {
        Mascota mascota = mascotaRepository.findById(mascotaId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se puede programar la cita: Mascota no encontrada con ID: " + mascotaId));

        cita.setMascota(mascota);
        return citaRepository.save(cita);
    }

    public Cita actualizar(Long id, Cita citaDetalles) {
        Cita citaExistente = obtenerPorId(id);

        citaExistente.setFecha(citaDetalles.getFecha());
        citaExistente.setMotivo(citaDetalles.getMotivo());
        citaExistente.setDiagnostico(citaDetalles.getDiagnostico());
        citaExistente.setCosto(citaDetalles.getCosto());

        return citaRepository.save(citaExistente);
    }

    public void eliminar(Long id) {
        Cita cita = obtenerPorId(id);
        citaRepository.delete(cita);
    }
}