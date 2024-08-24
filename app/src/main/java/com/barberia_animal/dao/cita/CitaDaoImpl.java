package com.barberia_animal.dao.cita;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import com.barberia_animal.models.Manager;
import com.barberia_animal.models.cita.Cita;
import com.barberia_animal.models.cita.CitaManager;
import com.barberia_animal.models.mascota.Mascota;
import com.barberia_animal.models.usuario.Usuario;

public class CitaDaoImpl implements CitaDao{

    private Manager dataManager = new CitaManager();

    public CitaDaoImpl(){
        dataManager.cargarDatos();
    }

    @Override
    public Cita actualizar(int id, Usuario usuario, LocalDateTime agenda, Mascota mascota) {
        Optional<Cita> opcional = filtrarCitaById(id);
        Cita cita = null;
        if (opcional.isPresent()){
            cita = Cita.builder().id(id).agenda(agenda).usuario(usuario).mascota(mascota).build();
            eliminar(id);
            List<Cita> nuevaLista = CitaManager.getCitas();
            nuevaLista.add(cita);
            CitaManager.setCitas(nuevaLista, dataManager);;
        }
        return cita;
    }

    @Override
    public boolean eliminar(int idCita) {
        Optional<Cita> opcional = filtrarCitaById(idCita);
        boolean eliminado = false;
        if (opcional.isPresent()){
            Predicate<Cita> condicion = c -> c.getId() == idCita;
            List<Cita> citas = CitaManager.getCitas();
            eliminado = citas.removeIf(condicion);
            CitaManager.setCitas(citas, dataManager);
        }
        return eliminado;
    }

    @Override
    public Optional<Cita> getCitaById(int id) {
        Predicate<Cita> condicion = c-> c.getId() == id;
        Optional<Cita> opcional = CitaManager.getCitas().stream().filter(condicion).findAny();
        return opcional;
    }

    @Override
    public Cita guardar(Usuario usuario, LocalDateTime agenda, Mascota mascota) {
        Optional<Cita> citaOpcional = filtrarByAgenda(agenda);
        Cita citaNueva = null;
        if (!citaOpcional.isPresent()){
            citaNueva = Cita.builder().agenda(agenda).usuario(usuario).mascota(mascota).build();
            List<Cita> citas = CitaManager.getCitas();
            citas.add(citaNueva);
            CitaManager.setCitas(citas, dataManager);// guardelo
        }
        return citaNueva;
    }

    public Optional<Cita> filtrarByAgenda(LocalDateTime agenda){
        Predicate<Cita> condicion = c -> agenda.isBefore(agenda);
        Optional<Cita> opcional = CitaManager.getCitas().stream().filter(condicion).findAny();
        return opcional;
    }

    public Optional<Cita> filtrarCitaById(int id) {
        Predicate<Cita> filtro = u -> u.getId() == id;
        Optional<Cita> cita = CitaManager.getCitas().stream().filter(filtro).findFirst();
        return cita;
    }

    public Optional<List<Cita>> filtrarCitaByUsuarioId(int id) {
        Predicate<Cita> filtro = u -> u.getUsuario().getId() == id;
        Optional<List<Cita>> lista = Optional.of(CitaManager.getCitas().stream().filter(filtro).toList());
        return lista;
    }

    @Override
    public List<Cita> getAll() {
        return CitaManager.getCitas();
    }
    
}
