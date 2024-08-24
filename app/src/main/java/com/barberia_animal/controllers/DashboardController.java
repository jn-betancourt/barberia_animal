package com.barberia_animal.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.barberia_animal.App;
import com.barberia_animal.models.cita.Cita;
import com.barberia_animal.service.CitaService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;


public class DashboardController implements Initializable{

    private CitaService citaService = new CitaService();
    ObservableList<Cita> listaObservable = FXCollections.observableArrayList();
    @FXML
    ListView<Cita> citas;
    private List<Cita> citasDb;


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        citasDb = citaService.getAll();
        for(Cita cita: citasDb){
            listaObservable.add(cita);
        }
        citas.getItems().setAll(listaObservable);
        citas.setOnMouseClicked((MouseEvent event)->{
            Cita selectedItem = citas.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                CitaUD.elementoSeleccionado(selectedItem.getId());
            }
        });
    }

    public void crearCita(){
        // System.out.println("presionado");
        try{
            App.setRoot("nuevaCita");
        }catch (Exception e){
            e.printStackTrace(System.out);
        }
    }
}
