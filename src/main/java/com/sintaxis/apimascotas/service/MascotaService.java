package com.sintaxis.apimascotas.service;

import com.sintaxis.apimascotas.model.Mascota;
import com.sintaxis.apimascotas.repository.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Puntos del examen cubiertos:
// B. Estructura (Lógica de negocio)

@Service
public class MascotaService {

    @Autowired
    private MascotaRepository repositorio;

    public List<Mascota> obtenerTodas() {
        return repositorio.findAll();
    }

    public Mascota guardar(Mascota mascota) {
        return repositorio.save(mascota);
    }

    public Mascota obtenerPorId(Long id) {
        return repositorio.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        repositorio.deleteById(id);
    }
}