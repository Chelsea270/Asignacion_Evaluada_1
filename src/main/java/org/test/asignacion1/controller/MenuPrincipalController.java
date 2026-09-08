package org.test.asignacion1.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MenuPrincipalController {

    @FXML
    private AnchorPane pnlPrincipal;
    private ContextMenu menuContextual;

    @FXML
    private void initialize() {
        CrearMenuContextual();
    }

    private void CrearMenuContextual(){
        MenuItem itemEstudiantes = new MenuItem("Registro de Estudiantes");
        itemEstudiantes.setOnAction(e -> abrirFormEstudiantes());

        MenuItem itemPeliculas = new MenuItem("Registro de Peliculas");
        itemPeliculas.setOnAction(e -> abrirFormPeliculas());

        MenuItem itemAcercaDe = new MenuItem("Acerca de la desarrolladora");
        itemAcercaDe.setOnAction(e -> mostrarDesarrolladora());

        menuContextual = new ContextMenu(itemEstudiantes, itemPeliculas, new SeparatorMenuItem(), itemAcercaDe);
    }


    @FXML
    private void mostrarMenuContextual(ContextMenuEvent evento) {
        menuContextual.hide();
        menuContextual.show(pnlPrincipal, evento.getScreenX(), evento.getScreenY());
    }

    // ---------- Navegación ----------

    @FXML
    private void abrirFormEstudiantes() {
        abrirVentana("/org/test/asignacion1/registro-estudiantes.fxml",
                "Registro de Estudiantes");
    }

    @FXML
    private void abrirFormPeliculas() {
        abrirVentana("/org/test/asignacion1/registro-peliculas.fxml",
                "Registro de Películas");
    }

    private void abrirVentana(String rutaFxml, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaFxml));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle(titulo);
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR,
                    "Error",
                    "No se pudo abrir el formulario",
                    "Detalle: " + e.getMessage());
        }
    }

    // ---------- Alerts ----------

    @FXML
    private void mostrarDesarrolladora() {
        mostrarAlerta(Alert.AlertType.INFORMATION,
                "Información de la desarrolladora",
                "Asignación Evaluada #1 - JavaFX",
                "Desarrolladora: Chelsea Quintanilla\n"
                        + "Carrera: Ingeniería en Sistemas\n"
                        + "Asignatura: Programación de Aplicaciones de Escritorio\n"
                        + "Universidad Americana (UAM)\n"
                        + "Año: 2026");
    }

    @FXML
    private void mostrarInstrucciones() {
        mostrarAlerta(Alert.AlertType.INFORMATION,
                "Instrucciones",
                "Cómo usar la aplicación",
                "1. Menú Catálogo: abre los formularios de registro.\n"
                        + "2. Clic derecho en la ventana: muestra el menú contextual.\n"
                        + "3. Menú Desarrollador: muestra los datos de la autora.\n"
                        + "4. Menú Ayuda > Salir: cierra la aplicación.");
    }

    @FXML
    private void salirAplicacion() {
        Stage stage = (Stage) pnlPrincipal.getScene().getWindow();
        stage.close();
    }

    // ---------- Método reutilizable ----------

    private void mostrarAlerta(Alert.AlertType tipo, String titulo,
                               String encabezado, String contenido) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(encabezado);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}

