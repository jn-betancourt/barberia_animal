package com.barberia_animal.controllers;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import com.barberia_animal.App;
import com.barberia_animal.dto.MascotaDto;
import com.barberia_animal.dto.UsuarioDto;
import com.barberia_animal.models.mascota.Raza;
import com.barberia_animal.service.CitaService;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class CitaController implements Initializable{
    
    CitaService citaService = new CitaService();

    @FXML
    ChoiceBox<Raza> choiceBox;
    private Raza[] listaRazas = {
        Raza.CHIZU, Raza.PASTOR_ALEMAN, Raza.PASTOR_AMERICANO, Raza.PITBULL, Raza.OTRO  
    };

    @FXML
    ChoiceBox<Integer> hora;
    Integer[] horas = {
        9, 10, 11, 12, 13, 14, 15, 16, 17, 18
    };

    @FXML
    TextField nombre;
    @FXML
    TextField email;
    @FXML
    TextField contraseña;
    @FXML
    TextField mascota;
    @FXML
    TextField condiciones;
    @FXML
    DatePicker fecha;
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        choiceBox.getItems().addAll(listaRazas);
        hora.getItems().addAll(horas);
    }

    public void guardar(){        
        LocalDateTime fehcaNueva = LocalDateTime.of(fecha.getValue().getYear(), fecha.getValue().getMonth(), fecha.getValue().getDayOfMonth(), hora.getValue(), 0, 0);
        MascotaDto mascotaNueva = new MascotaDto(0, mascota.getText(), choiceBox.getValue(), condiciones.getText());
        UsuarioDto usuarioNuevo = new UsuarioDto(0, nombre.getText(), email.getText(), contraseña.getText(), null);
        citaService.crearCita(usuarioNuevo, mascotaNueva, fehcaNueva);
        cancelar();
    }

    public void cancelar(){
        try {
            App.setRoot("dashboard");
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

}
