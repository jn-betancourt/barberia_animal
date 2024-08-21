package com.barberia_animal.dto;

import java.time.LocalDateTime;

public record CitaDto(int id, UsuarioDto usuario, MascotaDto mascota, LocalDateTime agenda) {
    
}
