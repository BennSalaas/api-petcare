package com.petcare.api_petcare.controller;

import com.petcare.api_petcare.entity.Dueno;
import com.petcare.api_petcare.entity.Mascota;
import com.petcare.api_petcare.service.DuenoService;
import com.petcare.api_petcare.service.MascotaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/duenos")
@CrossOrigin(origins = "*")
public class DuenoController {

    private final DuenoService duenoService;
    private final MascotaService mascotaService;

    public DuenoController(DuenoService duenoService, MascotaService mascotaService) {
        this.duenoService = duenoService;
        this.mascotaService = mascotaService;
    }

    // GET: Obtener todos los dueños
    @GetMapping
    public ResponseEntity<List<Dueno>> obtenerTodos() {
        return ResponseEntity.ok(duenoService.obtenerTodos());
    }

    // GET: Obtener dueño por ID
    @GetMapping("/{id}")
    public ResponseEntity<Dueno> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(duenoService.obtenerPorId(id));
    }

    // POST: Crear nuevo dueño
    @PostMapping
    public ResponseEntity<Dueno> guardar(@RequestBody Dueno dueno) {
        return new ResponseEntity<>(duenoService.guardar(dueno), HttpStatus.CREATED);
    }

    // PUT: Actualizar dueño
    @PutMapping("/{id}")
    public ResponseEntity<Dueno> actualizar(@PathVariable Long id, @RequestBody Dueno dueno) {
        return ResponseEntity.ok(duenoService.actualizar(id, dueno));
    }

    // DELETE: Eliminar dueño
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        duenoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint de relación exigido: GET /api/duenos/{id}/mascotas
    @GetMapping("/{id}/mascotas")
    public ResponseEntity<List<Mascota>> obtenerMascotasPorDueno(@PathVariable Long id) {
        return ResponseEntity.ok(mascotaService.obtenerPorDuenoId(id));
    }
}