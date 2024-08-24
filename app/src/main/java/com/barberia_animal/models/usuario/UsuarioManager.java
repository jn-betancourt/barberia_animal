package com.barberia_animal.models.usuario;

import java.util.List;
import java.io.File;
import java.io.IOException;

import com.barberia_animal.models.Manager;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UsuarioManager implements Manager{
    private static final String pathInfo = "app\\src\\main\\resources\\com\\barberia_animal\\db\\users.json";
    private static List<Usuario> usuarios;

    public void cargarDatos() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            usuarios = objectMapper.readValue(new File(pathInfo), new TypeReference<List<Usuario>>() {
            });
            // Seguir con el conteo del id
            if (usuarios.size() > 0) {
                Usuario us = usuarios.get(usuarios.size() - 1);
                Usuario.setContador(us.getId());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void guardarDatos() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(pathInfo), usuarios);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Usuario> getUsuarios() {
        return usuarios;
    }

    public static void setUsuarios(List<Usuario> usuarios, Manager manager) {
        UsuarioManager.usuarios = usuarios;
        manager.guardarDatos(); // Guardar datos inmediatamente después de modificar
    }
}