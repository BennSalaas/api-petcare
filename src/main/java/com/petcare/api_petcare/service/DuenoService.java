package com.petcare.api_petcare.service;

import com.petcare.api_petcare.entity.Dueno;
import com.petcare.api_petcare.repository.DuenoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class DuenoService {

    private final DuenoRepository duenoRepository;

    public DuenoService(DuenoRepository duenoRepository) {
        this.duenoRepository = duenoRepository;
    }

    public List<Dueno> obtenerTodos() {
        return duenoRepository.findAll();
    }

    public Dueno obtenerPorId(Long id) {
        return duenoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Dueño no encontrado con ID: " + id));
    }

    public Dueno guardar(Dueno dueno) {
        if (duenoRepository.existsByRut(dueno.getRut())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ya existe un dueño registrado con el RUT: " + dueno.getRut());
        }
        return duenoRepository.save(dueno);
    }

    public Dueno actualizar(Long id, Dueno duenoDetalles) {
        Dueno duenoExistente = obtenerPorId(id);

        duenoExistente.setNombre(duenoDetalles.getNombre());
        duenoExistente.setTelefono(duenoDetalles.getTelefono());
        duenoExistente.setEmail(duenoDetalles.getEmail());
        // El RUT por regla de negocio usualmente no cambia, pero si viene modificado se puede actualizar

        return duenoRepository.save(duenoExistente);
    }

    public void eliminar(Long id) {
        Dueno dueno = obtenerPorId(id);
        duenoRepository.delete(dueno);
    }
}