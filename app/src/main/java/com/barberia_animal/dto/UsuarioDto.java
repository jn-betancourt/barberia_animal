package com.barberia_animal.dto;

public record UsuarioDto(

    int id,
    String nombre,
    String email,
    String contraseña,
    List<Mascota>mascotas) {

}
