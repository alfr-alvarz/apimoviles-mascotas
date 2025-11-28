package com.sintaxis.apimascotas.service;

import com.sintaxis.apimascotas.model.Mascota;
import com.sintaxis.apimascotas.model.Usuario;
import com.sintaxis.apimascotas.repository.MascotaRepository;
import com.sintaxis.apimascotas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
// B. Estructura (Lógica de negocio)

@Service
public class MascotaService {

    @Autowired
    private MascotaRepository repositorio;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Mascota> obtenerTodas() {
        return repositorio.findAll();
    }

    public Mascota guardar(Mascota mascota) {
        return repositorio.save(mascota);
    }

    public Mascota guardar(Mascota mascota, Long idUsuario) {
        Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);
        if (usuario.isPresent()) {
            mascota.setUsuario(usuario.get());
        }
        return repositorio.save(mascota);
    }

    public Mascota obtenerPorId(Long id) {
        return repositorio.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        repositorio.deleteById(id);
    }
}