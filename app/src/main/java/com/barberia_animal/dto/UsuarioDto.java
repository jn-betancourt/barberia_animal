package com.barberia_animal.dto;

import java.util.List;

import com.barberia_animal.models.mascota.Mascota;

import lombok.Setter;

public record UsuarioDto(
    int id,
    String nombre,
    String email,
    String contraseña,
    List<Mascota> mascotas
) {
}
