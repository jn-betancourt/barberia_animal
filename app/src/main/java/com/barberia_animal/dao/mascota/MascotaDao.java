package com.barberia_animal.dao.mascota;

import com.barberia_animal.models.Mascota;
import com.barberia_animal.models.Raza;

public interface MascotaDao {
    Mascota guardar(String nombre, Raza raza, String condiciones);
    boolean eliminar(int idMascota);
    Mascota actualizar(int id, String nombre, Raza raza, String condiciones);
    Mascota getMascotaById(int id);
}
