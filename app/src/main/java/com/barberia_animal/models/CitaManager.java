package com.barberia_animal.models;

import java.util.List;
import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CitaManager implements Manager{
    private static final String pathInfo = "src/main/resources/com/Barberia_Animal/db/users.json";
    private static List<Cita> citas;

    public void cargarDatos() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            citas = objectMapper.readValue(new File(pathInfo), new TypeReference<List<Cita>>() {
            });
            // Seguir con el conteo del id
            if (citas.size() > 0) {
                Cita cit = citas.get(citas.size() - 1);
                Cita.setContador(cit.getId());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void guardarDatos() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(pathInfo), citas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Cita> getCitas() {
        return citas;
    }

    public static void setCitas(List<Cita> citas, Manager manager) {
        CitaManager.citas = citas;
        manager.guardarDatos(); // Guardar datos inmediatamente después de modificar
    }
}
