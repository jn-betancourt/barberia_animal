module com.barberia_animal {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires javafx.base;
    requires javafx.graphics;


    opens com.barberia_animal.controllers to javafx.fxml;

    exports com.barberia_animal.controllers to javafx.fxml;
    exports com.barberia_animal;
    exports com.barberia_animal.dao.cita;
    exports com.barberia_animal.dao.mascota;
    exports com.barberia_animal.dao.usuario;
    exports com.barberia_animal.models.cita;
    exports com.barberia_animal.models.usuario;
    exports com.barberia_animal.models.mascota;
    exports com.barberia_animal.service;
}
