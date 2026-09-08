package org.test.asignacion1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MenuPrincipalAplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(
                MenuPrincipalAplication.class.getResource("menu-principal.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Asignación Evaluada #1 - Menú Principal");
        stage.setScene(scene);
        stage.show();

    }
}
