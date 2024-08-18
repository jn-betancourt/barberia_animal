package com.barberia_animal.models;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter

public class Cita {
    private static int contadorObjetos = 0;
    private int id;
    private Usuario usuario;
    private Mascota mascota;
    private LocalDateTime agenda;

    public Cita() {
    }

    public Cita(int id, Usuario usuario, Mascota mascota, LocalDateTime agenda) {

        if (id == 0) {
            int idNuevo = ++Cita.contadorObjetos;
            Cita.contadorObjetos = idNuevo;
            this.id = idNuevo;
        } else {
            this.id = id;
        }
        this.usuario = usuario;
        this.mascota = mascota;
        this.agenda = agenda;

        }

    public static void setContador(int cit) {
        System.out.println(cit);
        Cita.contadorObjetos = cit;
    }


}
