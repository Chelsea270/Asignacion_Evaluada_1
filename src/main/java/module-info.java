module org.test.asignacion1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.test.asignacion1 to javafx.fxml;
    opens org.test.asignacion1.controller to javafx.fxml;
    opens org.test.asignacion1.model to javafx.base;

    exports org.test.asignacion1;
    exports org.test.asignacion1.model;
}