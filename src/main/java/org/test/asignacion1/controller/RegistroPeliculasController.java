package org.test.asignacion1.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.test.asignacion1.model.Pelicula;

public class RegistroPeliculasController {

    @FXML
    private TextField txtTitulo;
    @FXML
    private TextField txtDirector;
    @FXML
    private TextField txtGenero;
    @FXML
    private TextField txtAnio;
    @FXML
    private TextField txtDuracion;

    @FXML
    private TableView<Pelicula> tblPeliculas;
    @FXML
    private TableColumn<Pelicula, String>  colTitulo;
    @FXML
    private TableColumn<Pelicula, String>  colDirector;
    @FXML
    private TableColumn<Pelicula, String>  colGenero;
    @FXML
    private TableColumn<Pelicula, Integer> colAnio;
    @FXML
    private TableColumn<Pelicula, Integer> colDuracion;

    private final ObservableList<Pelicula> peliculas = FXCollections.observableArrayList();


    @FXML
    private void initialize() {
        configurarTabla();
        configurarSeleccion();
        cargarDatosPorDefecto();
    }

    private void configurarTabla() {
        colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colDirector.setCellValueFactory(new PropertyValueFactory<>("director"));
        colGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
        colAnio.setCellValueFactory(new PropertyValueFactory<>("anio"));
        colDuracion.setCellValueFactory(new PropertyValueFactory<>("duracion"));

        tblPeliculas.setItems(peliculas);
    }

    private void configurarSeleccion() {
        tblPeliculas.getSelectionModel().selectedItemProperty()
                .addListener((observable, valorAnterior, valorNuevo) -> {
                    if (valorNuevo != null) {
                        cargarPeliculaEnFormulario(valorNuevo);
                    }
                });
    }

    private void cargarDatosPorDefecto() {
        peliculas.addAll(
                new Pelicula("El Padrino", "Francis Ford Coppola", "Drama", 1972, 175),
                new Pelicula("Coco", "Lee Unkrich", "Animación", 2017, 105),
                new Pelicula("Parásitos", "Bong Joon-ho", "Suspenso", 2019, 132),
                new Pelicula("Interestelar", "Christopher Nolan", "Ciencia ficción", 2014, 169),
                new Pelicula("La La Land", "Damien Chazelle", "Musical", 2016, 128)
        );
    }

    private void cargarPeliculaEnFormulario(Pelicula pelicula) {
        txtTitulo.setText(pelicula.getTitulo());
        txtDirector.setText(pelicula.getDirector());
        txtGenero.setText(pelicula.getGenero());
        txtAnio.setText(String.valueOf(pelicula.getAnio()));
        txtDuracion.setText(String.valueOf(pelicula.getDuracion()));
    }

    @FXML
    private void agregarPelicula() {
        if (!validarCampos()) {
            return;
        }

        Pelicula pelicula = new Pelicula(
                txtTitulo.getText().trim(),
                txtDirector.getText().trim(),
                txtGenero.getText().trim(),
                Integer.parseInt(txtAnio.getText().trim()),
                Integer.parseInt(txtDuracion.getText().trim())
        );

        peliculas.add(pelicula);
        limpiarCampos();

        mostrarAlerta(Alert.AlertType.INFORMATION,
                "Registro exitoso",
                "Película agregada",
                "\"" + pelicula.getTitulo() + "\" se agregó a la tabla.");
    }

    @FXML
    private void limpiarCampos() {
        txtTitulo.clear();
        txtDirector.clear();
        txtGenero.clear();
        txtAnio.clear();
        txtDuracion.clear();
        tblPeliculas.getSelectionModel().clearSelection();
        txtTitulo.requestFocus();
    }

    @FXML
    private void mostrarInformacion() {
        mostrarAlerta(Alert.AlertType.INFORMATION,
                "Información",
                "Registro de Películas",
                "Películas en el catálogo: " + peliculas.size() + "\n\n"
                        + "Haga clic en una fila de la tabla para cargar sus datos.\n"
                        + "El año y la duración deben ser números enteros.\n"
                        + "El botón Limpiar vacía el formulario y quita la selección.");
    }

    private boolean validarCampos() {
        if (txtTitulo.getText().isBlank()
                || txtDirector.getText().isBlank()
                || txtGenero.getText().isBlank()
                || txtAnio.getText().isBlank()
                || txtDuracion.getText().isBlank()) {

            mostrarAlerta(Alert.AlertType.WARNING,
                    "Campos vacíos",
                    "Faltan datos por completar",
                    "Debe llenar los cinco campos antes de agregar la película.");
            return false;
        }

        try {
            Integer.parseInt(txtAnio.getText().trim());
            Integer.parseInt(txtDuracion.getText().trim());
        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.ERROR,
                    "Dato inválido",
                    "El año y la duración deben ser números",
                    "Ejemplo: Año = 2019, Duración = 132");
            return false;
        }

        return true;
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