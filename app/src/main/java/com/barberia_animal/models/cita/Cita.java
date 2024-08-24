package com.barberia_animal.models.cita;

import java.time.LocalDateTime;

import com.barberia_animal.models.mascota.Mascota;
import com.barberia_animal.models.usuario.Usuario;

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

    

    @Override
    public String toString() {
        return "ID CITA: "+this.getId()+"   Nombre de la mascota: "+this.getMascota().getNombre()+" en el mes de: "+this.getAgenda().getMonth()+" El dia: "+this.getAgenda().getDayOfMonth()+ " a las: "+ this.getAgenda().getHour()+" horas";
    }

}
