package com.sintaxis.apimascotas.controller;

import com.sintaxis.apimascotas.model.Usuario;
import com.sintaxis.apimascotas.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Obtener todos los usuarios
    @GetMapping
    public List<Usuario> listar() {
        return usuarioService.obtenerTodos();
    }

    // Registrar usuario
    @PostMapping
    public Usuario guardar(@RequestBody Usuario usuario) {
        return usuarioService.guardar(usuario);
    }

    // Editar usuario (Usa el mismo guardar si envías el ID en el cuerpo, o este específico)
    @PutMapping("/{id}")
    public Usuario editar(@PathVariable Long id, @RequestBody Usuario usuario) {
        usuario.setId(id); // Aseguramos que edite el ID correcto
        return usuarioService.guardar(usuario);
    }
    
    // Obtener usuario por ID
    @GetMapping("/{id}")
    public Usuario obtener(@PathVariable Long id) {
        return usuarioService.obtenerPorId(id);
    }
}