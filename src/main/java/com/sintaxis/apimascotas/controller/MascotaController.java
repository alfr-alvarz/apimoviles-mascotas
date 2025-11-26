package com.sintaxis.apimascotas.controller;

import com.sintaxis.apimascotas.model.Mascota;
import com.sintaxis.apimascotas.service.MascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/mascotas") 
@CrossOrigin(origins = "*")
public class MascotaController {

    @Autowired
    private MascotaService servicio;

    
    @GetMapping
    public List<Mascota> listar() {
        return servicio.obtenerTodas();
    }

 
    @PostMapping
    public Mascota guardar(@RequestBody Mascota mascota) {
        return servicio.guardar(mascota);
    }

    
    @GetMapping("/{id}")
    public Mascota obtener(@PathVariable Long id) {
        return servicio.obtenerPorId(id);
    }

    
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        servicio.eliminar(id);
    }
}