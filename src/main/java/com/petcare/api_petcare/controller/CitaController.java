package com.petcare.api_petcare.controller;

import com.petcare.api_petcare.entity.Cita;
import com.petcare.api_petcare.service.CitaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/citas")
@CrossOrigin(origins = "*")
public class CitaController {

    private final CitaService citaService;

    public CitaController(CitaService citaService) {
        this.citaService = citaService;
    }

    // GET: Obtener todas las citas
    @GetMapping
    public ResponseEntity<List<Cita>> obtenerTodas() {
        return ResponseEntity.ok(citaService.obtenerTodas());
    }

    // GET: Obtener cita por ID
    @GetMapping("/{id}")
    public ResponseEntity<Cita> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(citaService.obtenerPorId(id));
    }

    // POST: Crear cita asociada a una mascota (pasando mascotaId por parámetro)
    @PostMapping
    public ResponseEntity<Cita> guardar(@RequestBody Cita cita, @RequestParam Long mascotaId) {
        return new ResponseEntity<>(citaService.guardar(cita, mascotaId), HttpStatus.CREATED);
    }

    // PUT: Actualizar cita
    @PutMapping("/{id}")
    public ResponseEntity<Cita> actualizar(@PathVariable Long id, @RequestBody Cita cita) {
        return ResponseEntity.ok(citaService.actualizar(id, cita));
    }

    // DELETE: Eliminar cita
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        citaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
