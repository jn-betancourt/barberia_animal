package com.barberia_animal.dto;

import java.util.List;

import com.barberia_animal.models.Mascota;

public record UsuarioDto(
    int id,
    String nombre,
    String email,
    String contraseña,
    List<Mascota> mascotas
) {
}
