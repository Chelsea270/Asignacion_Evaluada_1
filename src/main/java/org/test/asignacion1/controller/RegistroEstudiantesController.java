package org.test.asignacion1.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class RegistroEstudiantesController {
    @FXML
    private TextField txtCarnet;
    @FXML
    private TextField txtNombres;
    @FXML
    private TextField txtApellidos;
    @FXML
    private TextField txtCarrera;
    @FXML
    private TextField txtCorreo;

    @FXML
    private TextArea txtAreaEstudiantes;

    private int contadorEstudiantes = 0;

    @FXML
    private void guardarEstudiante() {
        if (!validarCampos()) {
            return;
        }

        contadorEstudiantes++;

        String registro =
                "Estudiante #" + contadorEstudiantes + "\n"
                        + "Carnet    : " + txtCarnet.getText().trim() + "\n"
                        + "Nombres   : " + txtNombres.getText().trim() + "\n"
                        + "Apellidos : " + txtApellidos.getText().trim() + "\n"
                        + "Carrera   : " + txtCarrera.getText().trim() + "\n"
                        + "Correo    : " + txtCorreo.getText().trim() + "\n"
                        + "------------------------------------------\n";

        txtAreaEstudiantes.appendText(registro);

        mostrarAlerta(Alert.AlertType.INFORMATION,
                "Registro exitoso",
                "Estudiante guardado correctamente",
                "Se registró a " + txtNombres.getText().trim()
                        + " " + txtApellidos.getText().trim() + ".");

        limpiarCampos();
    }

    private boolean validarCampos() {
        if (txtCarnet.getText().isBlank()
                || txtNombres.getText().isBlank()
                || txtApellidos.getText().isBlank()
                || txtCarrera.getText().isBlank()
                || txtCorreo.getText().isBlank()) {

            mostrarAlerta(Alert.AlertType.WARNING,
                    "Campos vacíos",
                    "Faltan datos por completar",
                    "Debe llenar los cinco campos antes de guardar el registro.");
            return false;
        }
        return true;
    }

    @FXML
    private void limpiarCampos() {
        txtCarnet.clear();
        txtNombres.clear();
        txtApellidos.clear();
        txtCarrera.clear();
        txtCorreo.clear();
        txtCarnet.requestFocus();
    }

    @FXML
    private void mostrarInformacion() {
        mostrarAlerta(Alert.AlertType.INFORMATION,
                "Información",
                "Registro de Estudiantes",
                "Estudiantes registrados en esta sesión: " + contadorEstudiantes + "\n\n"
                        + "Complete los cinco campos y presione Guardar.\n"
                        + "Los registros se muestran en el área de texto inferior.\n"
                        + "El botón Limpiar borra los campos del formulario.");
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo,
                               String encabezado, String contenido) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(encabezado);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}
