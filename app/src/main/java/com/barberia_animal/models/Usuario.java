package com.barberia_animal.models;

import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter

public class Usuario {
    private static int contadorObjetos = 0;
    private int id;
    private String nombre;
    private String email;
    private String contraseña;
    private List<Mascota> mascotas;
    

    public Usuario() {
    }

    public Usuario(int id, String nombre, String email, String contraseña, List<Mascota> mascotas) {

        if (id == 0) {
            int idNuevo = ++Usuario.contadorObjetos;
            Usuario.contadorObjetos = idNuevo;
            this.id = idNuevo;
        } else {
            this.id = id;
        }
        this.nombre = nombre;
        this.email = email;
        this.contraseña = contraseña;
        this.mascotas = new ArrayList<>();

        }

    public static void setContador(int num) {
        System.out.println(num);
        Usuario.contadorObjetos = num;
    }

}
