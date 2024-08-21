package com.barberia_animal.dao.usuario;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import com.barberia_animal.models.Manager;
import com.barberia_animal.models.Mascota;
import com.barberia_animal.models.Usuario;
import com.barberia_animal.models.UsuarioManager;

public class UsuarioDaoImpl implements UsuarioDao{
    private Manager dataManager = new UsuarioManager();

    public UsuarioDaoImpl() {
        dataManager.cargarDatos();
    }

    @Override
    public Usuario actualizar(int id, String nombre, String email, String contraseña, List<Mascota> mascotas) {
        Optional<Usuario> usuario = filtrarUsuarioById(id);
        Usuario us = null;
        if (usuario.isPresent()){
            us = Usuario.builder().nombre(nombre).email(email).contraseña(contraseña).mascotas(mascotas).id(id).build();
            eliminar(id);
            List<Usuario> nuevaLista = UsuarioManager.getUsuarios();
            nuevaLista.add(us);
            UsuarioManager.setUsuarios(nuevaLista, dataManager);
        }
        return us;
    }

    @Override
    public boolean eliminar(int id) {
        Optional<Usuario> usuario = filtrarUsuarioById(id);
        boolean eliminado = false;
        if(usuario.isPresent()){
            List<Usuario> nuevaLista = UsuarioManager.getUsuarios();
            Predicate<Usuario> condicion = u -> u.getId() == usuario.get().getId();
            eliminado = nuevaLista.removeIf(condicion);
            UsuarioManager.setUsuarios(nuevaLista, dataManager);
        }
        return eliminado;
    }

    @Override
    public Usuario guardar(String nombre, String email, String contraseña, List<Mascota> mascotas) {
        Optional<Usuario> usuario = filtrarUsuarioByEmail(email);
        Usuario nuevo = null;
        if (!usuario.isPresent()){
            nuevo = Usuario.builder().id(0).email(email).contraseña(contraseña).nombre(nombre).build();
            List<Usuario> usuarios = UsuarioManager.getUsuarios(); // modifique la liste de usuarios
            usuarios.add(nuevo);// añada el nuevo usuario
            UsuarioManager.setUsuarios(usuarios, dataManager); // guardelo
        }
        return nuevo;
    }

    public Optional<Usuario> filtrarUsuarioByEmail(String email) {
        Predicate<Usuario> filtro = u -> u.getEmail().equals(email);
        Optional<Usuario> usuario = UsuarioManager.getUsuarios().stream().filter(filtro).findFirst();  
        return usuario;
    }

    public Optional<Usuario> filtrarUsuarioById(int id) {
        Predicate<Usuario> filtro = u -> u.getId() == id;
        Optional<Usuario> usuario = UsuarioManager.getUsuarios().stream().filter(filtro).findFirst();
        return usuario;
    }

    public List<Usuario> getAll() {
        return UsuarioManager.getUsuarios();
    }
}
