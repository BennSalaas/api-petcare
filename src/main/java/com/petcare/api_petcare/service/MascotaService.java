package com.petcare.api_petcare.service;

import com.petcare.api_petcare.entity.Dueno;
import com.petcare.api_petcare.entity.Mascota;
import com.petcare.api_petcare.repository.DuenoRepository;
import com.petcare.api_petcare.repository.MascotaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MascotaService {

    private final MascotaRepository mascotaRepository;
    private final DuenoRepository duenoRepository;

    public MascotaService(MascotaRepository mascotaRepository, DuenoRepository duenoRepository) {
        this.mascotaRepository = mascotaRepository;
        this.duenoRepository = duenoRepository;
    }

    public List<Mascota> obtenerTodas() {
        return mascotaRepository.findAll();
    }

    public Mascota obtenerPorId(Long id) {
        return mascotaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Mascota no encontrada con ID: " + id));
    }

    public List<Mascota> obtenerPorDuenoId(Long duenoId) {
        // Valida que el dueño exista antes de buscar sus mascotas
        if (!duenoRepository.existsById(duenoId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Dueño no encontrado con ID: " + duenoId);
        }
        return mascotaRepository.findByDuenoId(duenoId);
    }

    public Mascota guardar(Mascota mascota, Long duenoId) {
        Dueno dueno = duenoRepository.findById(duenoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se puede crear la mascota: Dueño no encontrado con ID: " + duenoId));

        mascota.setDueno(dueno);
        return mascotaRepository.save(mascota);
    }

    public Mascota actualizar(Long id, Mascota mascotaDetalles) {
        Mascota mascotaExistente = obtenerPorId(id);

        mascotaExistente.setNombre(mascotaDetalles.getNombre());
        mascotaExistente.setEspecie(mascotaDetalles.getEspecie());
        mascotaExistente.setRaza(mascotaDetalles.getRaza());
        mascotaExistente.setFechaNacimiento(mascotaDetalles.getFechaNacimiento());

        return mascotaRepository.save(mascotaExistente);
    }

    public void eliminar(Long id) {
        Mascota mascota = obtenerPorId(id);
        mascotaRepository.delete(mascota);
    }
}