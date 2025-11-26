package com.sintaxis.apimascotas.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "mascotas")
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String tipo; // Perro, Gato, Ave, Otro

    // Relación Muchos a Uno: Muchas mascotas pertenecen a un usuario
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonIgnoreProperties("mascotas") // Evita bucles infinitos
    private Usuario usuario;

    // --- CONSTRUCTORES ---
    public Mascota() {
    }

    public Mascota(String nombre, String tipo, Usuario usuario) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.usuario = usuario;
    }

    // --- GETTERS Y SETTERS OBLIGATORIOS ---
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    // ¡ESTOS SON LOS QUE FALTABAN!
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}