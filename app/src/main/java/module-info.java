module com.barberia_animal {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.barberia_animal to javafx.fxml;
    exports com.barberia_animal;
}
