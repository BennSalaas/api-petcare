package com.petcare.api_petcare.controller;

import com.petcare.api_petcare.entity.Cita;
import com.petcare.api_petcare.entity.Mascota;
import com.petcare.api_petcare.service.CitaService;
import com.petcare.api_petcare.service.MascotaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mascotas")
@CrossOrigin(origins = "*")
public class MascotaController {

    private final MascotaService mascotaService;
    private final CitaService citaService;

    public MascotaController(MascotaService mascotaService, CitaService citaService) {
        this.mascotaService = mascotaService;
        this.citaService = citaService;
    }

    // GET: Obtener todas las mascotas
    @GetMapping
    public ResponseEntity<List<Mascota>> obtenerTodas() {
        return ResponseEntity.ok(mascotaService.obtenerTodas());
    }

    // GET: Obtener mascota por ID
    @GetMapping("/{id}")
    public ResponseEntity<Mascota> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(mascotaService.obtenerPorId(id));
    }

    // POST: Crear mascota asociada a un dueño (pasando el duenoId por parámetro)
    @PostMapping
    public ResponseEntity<Mascota> guardar(@RequestBody Mascota mascota, @RequestParam Long duenoId) {
        return new ResponseEntity<>(mascotaService.guardar(mascota, duenoId), HttpStatus.CREATED);
    }

    // PUT: Actualizar mascota
    @PutMapping("/{id}")
    public ResponseEntity<Mascota> actualizar(@PathVariable Long id, @RequestBody Mascota mascota) {
        return ResponseEntity.ok(mascotaService.actualizar(id, mascota));
    }

    // DELETE: Eliminar mascota
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        mascotaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint de relación exigido: GET /api/mascotas/{id}/citas
    @GetMapping("/{id}/citas")
    public ResponseEntity<List<Cita>> obtenerCitasPorMascota(@PathVariable Long id) {
        return ResponseEntity.ok(citaService.obtenerPorMascotaId(id));
    }
}
