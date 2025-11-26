package com.sintaxis.apimascotas.dto;

import jakarta.validation.constraints.*;
import java.util.List;

public class RegistroRequest {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 50, message = "El nombre no puede exceder 50 caracteres")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "El nombre solo puede contener letras y espacios")
    private String nombreCompleto;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "Formato de correo inválido")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@duoc\\.cl$", message = "El correo debe ser @duoc.cl")
    private String correo;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).*$", 
             message = "La contraseña debe tener mayúscula, minúscula, número y carácter especial (@#$%^&+=!)")
    private String contrasena;

    private String telefono;


    @NotEmpty(message = "Debes registrar al menos una mascota")
    private List<MascotaDTO> mascotas;


    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public List<MascotaDTO> getMascotas() { return mascotas; }
    public void setMascotas(List<MascotaDTO> mascotas) { this.mascotas = mascotas; }
}