package com.barberia_animal.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.barberia_animal.dao.cita.CitaDao;
import com.barberia_animal.dao.cita.CitaDaoImpl;
import com.barberia_animal.dao.mascota.MascotaDao;
import com.barberia_animal.dao.mascota.MascotaDaoImpl;
import com.barberia_animal.dao.usuario.UsuarioDao;
import com.barberia_animal.dao.usuario.UsuarioDaoImpl;
import com.barberia_animal.dto.CitaDto;
import com.barberia_animal.dto.MascotaDto;
import com.barberia_animal.dto.UsuarioDto;
import com.barberia_animal.models.cita.Cita;
import com.barberia_animal.models.mascota.Mascota;
import com.barberia_animal.models.usuario.Usuario;

public class CitaService {
    private CitaDao citaDao;
    private UsuarioDao usuarioDao;
    private MascotaDao mascotaDao;


    public CitaService() {
        this.citaDao = new CitaDaoImpl();
        this.usuarioDao = new UsuarioDaoImpl();
        this.mascotaDao = new MascotaDaoImpl();
    }

    // Convierte un objeto Cita a CitaDto
    private CitaDto convertirToDto(Cita cita) {
        CitaDto citaDto = null;
        if (cita != null) {
            UsuarioDto usuarioDto = convertirToDto(cita.getUsuario());
            MascotaDto mascotaDto = convertirToDto(cita.getMascota());
            citaDto = new CitaDto(cita.getId(), usuarioDto, mascotaDto, cita.getAgenda());
        }
        return citaDto;
    }

    // Convierte un objeto CitaDto a Cita
    private Cita convertirToCita(CitaDto citaDto) {
        Cita cita = null;
        if (citaDto != null) {
            Usuario usuario = convertirToUsuario(citaDto.usuario());
            Mascota mascota = convertirToMascota(citaDto.mascota());
            cita = new Cita(citaDto.id(), usuario, mascota, citaDto.agenda());
        }
        return cita;
    }

    // Convierte un objeto Mascota a MascotaDto
    private MascotaDto convertirToDto(Mascota mascota) {
        MascotaDto mascotaDto = null;
        if (mascota != null) {
            mascotaDto = new MascotaDto(mascota.getId(), mascota.getNombre(), mascota.getRaza(), mascota.getCondiciones());
        }
        return mascotaDto;
    }

    // Convierte un objeto MascotaDto a Mascota
    private Mascota convertirToMascota(MascotaDto mascotaDto) {
        Mascota mascota = null;
        if (mascotaDto != null) {
            mascota = new Mascota(mascotaDto.id(), mascotaDto.nombre(), mascotaDto.raza(), mascotaDto.condiciones());
        }
        return mascota;
    }

    // Convierte un objeto Usuario a UsuarioDto
    private UsuarioDto convertirToDto(Usuario usuario) {
        UsuarioDto usuarioDto = null;
        if (usuario != null) {
            usuarioDto = new UsuarioDto(usuario.getId(), usuario.getNombre(), usuario.getEmail(), usuario.getContraseña(), usuario.getMascotas());
        }
        return usuarioDto;
    }

    // Convierte un objeto UsuarioDto a Usuario
    private Usuario convertirToUsuario(UsuarioDto usuarioDto) {
        Usuario usuario = null;
        if (usuarioDto != null) {
            usuario = Usuario.builder().id(usuarioDto.id()).nombre(usuarioDto.nombre()).contraseña(usuarioDto.contraseña()).mascotas(usuarioDto.mascotas()).build();
        }
        return usuario;
    }

    // Métodos para gestionar citas
    public CitaDto crearCita(UsuarioDto usuario, MascotaDto mascota, LocalDateTime agenda){
        Mascota mascotaNueva = mascotaDao.guardar(mascota.nombre(), mascota.raza(), mascota.condiciones());
        Usuario usuarioNuevo = usuarioDao.guardar(usuario.nombre(), usuario.email(), usuario.contraseña(), List.of(mascotaNueva));
        Cita citaNueva = citaDao.guardar(usuarioNuevo, agenda, mascotaNueva);
        return convertirToDto(citaNueva);
    }
    public CitaDto obtenerCita(int idCita){
        Optional<Cita> cita = citaDao.getCitaById(idCita);
        CitaDto citaDto1 = null;
        if (cita.isPresent()){
            citaDto1 = convertirToDto(cita.get());
        }
        return citaDto1;
    };
    public CitaDto modificarCita(CitaDto citaDto){
        Usuario usuario = usuarioDao.actualizar(citaDto.usuario().id(), citaDto.usuario().nombre(), citaDto.usuario().email(), citaDto.usuario().contraseña(), citaDto.usuario().mascotas());
        Mascota mascota = mascotaDao.actualizar(citaDto.mascota().id(), citaDto.mascota().nombre(), citaDto.mascota().raza(), citaDto.mascota().condiciones());
        Cita cita = citaDao.actualizar(citaDto.id(), usuario, citaDto.agenda(), mascota);
        return convertirToDto(cita);
    };

    public boolean eliminarCita(CitaDto citaDto){
        boolean eliminado = usuarioDao.eliminar(citaDto.usuario().id());
        boolean eliminado2 = mascotaDao.eliminar(citaDto.mascota().id());
        boolean eliminado3 = citaDao.eliminar(citaDto.id());
        boolean eliminados = eliminado && eliminado2 && eliminado3;
        return eliminados;
    };

    public List<Cita> getAll(){
        return citaDao.getAll();
    }

}
