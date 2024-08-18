package com.barberia_animal.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter

public class Mascota {
    private static int contadorObjetos = 0;
    private int id;
    private String nombre;
    private Raza raza;
    private String condiciones;

    public Mascota() {
    }

    public Mascota(int id, String nombre, Raza raza, String condiciones) {

        if (id == 0) {
            int idNuevo = ++Mascota.contadorObjetos;
            Mascota.contadorObjetos = idNuevo;
            this.id = idNuevo;
        } else {
            this.id = id;
        }
        this.nombre = nombre;
        this.raza = raza;
        this.condiciones = condiciones;

        }

    public static void setContador(int mas) {
        System.out.println(mas);
        Mascota.contadorObjetos = mas;
    }

}


