package com.barberia_animal.dao.usuario;

import java.util.List;

import com.barberia_animal.models.mascota.Mascota;
import com.barberia_animal.models.usuario.Usuario;

public interface UsuarioDao {
    Usuario guardar(String nombre, String email, String contraseña, List<Mascota> mascotas);
    boolean eliminar(int id);
    Usuario actualizar(int id, String nombre, String email, String contraseña, List<Mascota> mascotas);
}
