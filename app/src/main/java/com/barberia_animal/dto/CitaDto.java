package com.barberia_animal.dto;

public record CitaDto(

    int id,
    Usuario usuario,
    Mascota mascota,
    LocalDateTime agenda) {
    
}
