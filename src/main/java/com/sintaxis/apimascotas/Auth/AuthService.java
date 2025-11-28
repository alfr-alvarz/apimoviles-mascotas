package com.sintaxis.apimascotas.Auth;

import com.sintaxis.apimascotas.dto.LoginRequest;
import com.sintaxis.apimascotas.dto.MascotaDTO;
import com.sintaxis.apimascotas.dto.RegistroRequest;
import com.sintaxis.apimascotas.model.Mascota;
import com.sintaxis.apimascotas.model.Usuario;
import com.sintaxis.apimascotas.repository.MascotaRepository;
import com.sintaxis.apimascotas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private MascotaRepository mascotaRepository;

    
    public Usuario registrar(RegistroRequest request) {
        
        if (usuarioRepository.findByCorreo(request.getCorreo()).isPresent()) {
            throw new RuntimeException("El correo ya está registrado");
        }

        
        Usuario usuario = new Usuario();
        usuario.setNombreCompleto(request.getNombreCompleto());
        usuario.setCorreo(request.getCorreo());
        usuario.setContrasena(request.getContrasena()); 
        usuario.setTelefono(request.getTelefono());
        
        
        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        
        if (request.getMascotas() != null) {
            for (MascotaDTO mDto : request.getMascotas()) {
                Mascota mascota = new Mascota();
                mascota.setNombre(mDto.getNombre());
                mascota.setTipo(mDto.getTipo());
                mascota.setUsuario(usuarioGuardado); 
                mascotaRepository.save(mascota);
            }
        }
        
        return usuarioGuardado;
    }


    public Usuario login(LoginRequest request) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByCorreo(request.getCorreo());
        
        if (usuarioOpt.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado");
        }

        Usuario usuario = usuarioOpt.get();
        
        if (!usuario.getContrasena().equals(request.getContrasena())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        return usuario;
    }
}