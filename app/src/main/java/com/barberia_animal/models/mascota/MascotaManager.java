package com.barberia_animal.models.mascota;

import java.util.List;
import java.io.File;
import java.io.IOException;

import com.barberia_animal.models.Manager;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MascotaManager implements Manager{
    private static final String pathInfo = "app\\src\\main\\resources\\com\\barberia_animal\\db\\mascotas.json";
    private static List<Mascota> mascotas;

    public  void cargarDatos() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            mascotas = objectMapper.readValue(new File(pathInfo), new TypeReference<List<Mascota>>() {
            });
            // Seguir con el conteo del id
            if (mascotas.size() > 0) {
                Mascota mas = mascotas.get(mascotas.size() - 1);
                Mascota.setContador(mas.getId());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  void guardarDatos() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(pathInfo), mascotas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Mascota> getMascotas() {
        return mascotas;
    }

    public static void setMascotas(List<Mascota> mascotas, Manager manager) {
        MascotaManager.mascotas = mascotas;
        manager.guardarDatos(); // Guardar datos inmediatamente después de modificar
    }
}