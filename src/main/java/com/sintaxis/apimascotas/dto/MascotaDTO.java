package com.sintaxis.apimascotas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class MascotaDTO {

    @NotBlank(message = "El nombre de la mascota es obligatorio")
    @Size(max = 50, message = "El nombre de la mascota max 50 caracteres")
    private String nombre;

    @NotBlank(message = "El tipo de mascota es obligatorio")
    @Pattern(regexp = "^(Perro|Gato|Ave|Otro)$", message = "El tipo debe ser: Perro, Gato, Ave u Otro")
    private String tipo;

    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
}