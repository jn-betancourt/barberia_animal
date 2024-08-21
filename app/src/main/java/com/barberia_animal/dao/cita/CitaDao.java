package com.barberia_animal.dao.cita;

import java.time.LocalDateTime;
import java.util.Optional;

import com.barberia_animal.models.Cita;
import com.barberia_animal.models.Mascota;
import com.barberia_animal.models.Usuario;

public interface CitaDao {
    Cita guardar(Usuario usuario, LocalDateTime agenda, Mascota mascota);
    boolean eliminar(int idCita);
    Cita actualizar(int id, Usuario usuario, LocalDateTime agenda, Mascota mascota);
    Optional<Cita> getCitaById(int id);
}
