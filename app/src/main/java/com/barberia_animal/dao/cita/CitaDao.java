package com.barberia_animal.dao.cita;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.barberia_animal.models.cita.Cita;
import com.barberia_animal.models.mascota.Mascota;
import com.barberia_animal.models.usuario.Usuario;

public interface CitaDao {
    Cita guardar(Usuario usuario, LocalDateTime agenda, Mascota mascota);
    boolean eliminar(int idCita);
    Cita actualizar(int id, Usuario usuario, LocalDateTime agenda, Mascota mascota);
    Optional<Cita> getCitaById(int id);
    List<Cita> getAll();
}
