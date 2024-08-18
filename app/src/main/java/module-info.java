module com.barberia_animal {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires javafx.base;


    opens com.barberia_animal to javafx.fxml;
    exports com.barberia_animal;
}
