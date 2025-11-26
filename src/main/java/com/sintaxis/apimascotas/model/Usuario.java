package com.sintaxis.apimascotas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // id_usuario

    private String nombreCompleto;
    private String correo;
    private String contrasena;
    private String telefono; // Opcional

    // Relación: Un usuario tiene muchas mascotas
    // mappedBy = "usuario" refiere al nombre del campo en la clase Mascota
    // cascade = CascadeType.ALL permite que si borras al usuario, se borren sus mascotas
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonIgnore // Evita bucles infinitos al convertir a JSON
    private List<Mascota> mascotas;

    public Usuario() {}

    public Usuario(String nombreCompleto, String correo, String contrasena, String telefono) {
        this.nombreCompleto = nombreCompleto;
        this.correo = correo;
        this.contrasena = contrasena;
        this.telefono = telefono;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public List<Mascota> getMascotas() { return mascotas; }
    public void setMascotas(List<Mascota> mascotas) { this.mascotas = mascotas; }
}