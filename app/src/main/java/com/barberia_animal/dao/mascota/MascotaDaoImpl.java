package com.barberia_animal.dao.mascota;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import com.barberia_animal.models.Manager;
import com.barberia_animal.models.Mascota;
import com.barberia_animal.models.MascotaManager;
import com.barberia_animal.models.Raza;

public class MascotaDaoImpl implements MascotaDao {
    private Manager dataManager;

    public MascotaDaoImpl(){
        dataManager = new MascotaManager();
    }

    @Override
    public Mascota actualizar(int id, String nombre, Raza raza, String condiciones) {
        Mascota mascota = getMascotaById(id);
        Mascota mascotaNueva = null;
        if (mascota != null){
            eliminar(id);
            mascotaNueva = Mascota.builder().id(id).nombre(nombre).raza(raza).condiciones(condiciones).build();
            List<Mascota> mascotas = MascotaManager.getMascotas();
            mascotas.add(mascotaNueva);
            MascotaManager.setMascotas(mascotas, dataManager);
        }
        return mascotaNueva;
    }

    @Override
    public boolean eliminar(int idMascota) {
        Mascota mascota = getMascotaById(idMascota);
        boolean eliminado = false;
        if (mascota != null){
            Predicate<Mascota> condicion = m -> m.getId() == idMascota;
            List<Mascota> mascotas = MascotaManager.getMascotas();
            eliminado = mascotas.removeIf(condicion);
            MascotaManager.setMascotas(mascotas, dataManager);
        }
        return eliminado;
    }

    @Override
    public Mascota getMascotaById(int id) {
        Predicate<Mascota> condicion = m -> m.getId() == id;
        Optional<Mascota> mascota = MascotaManager.getMascotas().stream().filter(condicion).findAny();
        return mascota.get();
    }

    @Override
    public Mascota guardar(String nombre, Raza raza, String condiciones) {
        Mascota nuevaMascota = Mascota.builder().nombre(nombre).raza(raza).condiciones(condiciones).build();
        return nuevaMascota;
    }
}
