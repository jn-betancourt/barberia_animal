package com.barberia_animal.dto;

import com.barberia_animal.models.Raza;

public record MascotaDto(
    int id,
    String nombre,
    Raza raza,
    String condiciones
) {
    
}
