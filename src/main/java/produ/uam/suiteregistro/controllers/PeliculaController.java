package produ.uam.suiteregistro.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import produ.uam.suiteregistro.models.Pelicula;
import produ.uam.suiteregistro.repository.PeliculaRepository;

public class PeliculaController {

    @FXML
    private TextField txtTitulo;
    @FXML
    private TextField txtDirector;
    @FXML
    private TextField txtGenero;
    @FXML
    private TextField txtAnioEstreno;
    @FXML
    private TextField txtDuracion;

    @FXML
    private Button btnRegistrar;
    @FXML
    private Button btnLimpiar;
    @FXML
    private Button btnInformacion;

    @FXML
    private TableView<Pelicula> tblPeliculas;
    @FXML
    private TableColumn<Pelicula, String> colTitulo;
    @FXML
    private TableColumn<Pelicula, String> colDirector;
    @FXML
    private TableColumn<Pelicula, String> colGenero;
    @FXML
    private TableColumn<Pelicula, String> colAnioEstreno;
    @FXML
    private TableColumn<Pelicula, String> colDuracion;

    private final PeliculaRepository peliculaRepository = new PeliculaRepository();

    @FXML
    private void initialize() {
        colTitulo.setCellValueFactory(cellData -> cellData.getValue().tituloProperty());
        colDirector.setCellValueFactory(cellData -> cellData.getValue().directorProperty());
        colGenero.setCellValueFactory(cellData -> cellData.getValue().generoProperty());
        colAnioEstreno.setCellValueFactory(cellData -> cellData.getValue().anioEstrenoProperty());
        colDuracion.setCellValueFactory(cellData -> cellData.getValue().duracionProperty());

        tblPeliculas.setItems(peliculaRepository.obtenerCatalogo());

        tblPeliculas.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, peliculaSeleccionada) -> {
            if (peliculaSeleccionada != null) {
                txtTitulo.setText(peliculaSeleccionada.getTitulo());
                txtDirector.setText(peliculaSeleccionada.getDirector());
                txtGenero.setText(peliculaSeleccionada.getGenero());
                txtAnioEstreno.setText(peliculaSeleccionada.getAnioEstreno());
                txtDuracion.setText(peliculaSeleccionada.getDuracion());
            }
        });
    }

    @FXML
    private void onRegistrarPelicula(ActionEvent event) {
        String titulo = txtTitulo.getText().trim();
        String director = txtDirector.getText().trim();
        String genero = txtGenero.getText().trim();
        String anio = txtAnioEstreno.getText().trim();
        String duracion = txtDuracion.getText().trim();

        if (titulo.isEmpty() || director.isEmpty() || genero.isEmpty() || anio.isEmpty() || duracion.isEmpty()) {
            mostrarAlerta(
                    AlertType.WARNING,
                    "Validación de Campos",
                    "Formulario incompleto",
                    "Debe completar todos los campos del formulario antes de procesar el registro."
            );
            return;
        }

        Pelicula nuevaPelicula = new Pelicula(titulo, director, genero, anio, duracion);
        peliculaRepository.agregarPelicula(nuevaPelicula);

        mostrarAlerta(
                AlertType.INFORMATION,
                "Registro Exitoso",
                null,
                "La película \"" + titulo + "\" ha sido registrada correctamente."
        );

        limpiarFormulario();
    }

    @FXML
    private void onLimpiarCampos(ActionEvent event) {
        limpiarFormulario();
    }

    @FXML
    private void onMostrarInformacion(ActionEvent event) {
        mostrarAlerta(
                AlertType.INFORMATION,
                "Información del Sistema",
                "Módulo de Control Cinematográfico",
                "Detalles del módulo:\n"
                        + "• Seleccionar una película en la tabla carga automáticamente su información en el formulario superior.\n"
                        + "• Para dar de alta una película, complete los 5 campos obligatorios y haga clic en 'Registrar'.\n"
                        + "• Use el botón 'Limpiar' para deseleccionar filas y restablecer las cajas de texto vacías."
        );
    }

    private void limpiarFormulario() {
        txtTitulo.clear();
        txtDirector.clear();
        txtGenero.clear();
        txtAnioEstreno.clear();
        txtDuracion.clear();
        tblPeliculas.getSelectionModel().clearSelection();
        txtTitulo.requestFocus();
    }

    private void mostrarAlerta(AlertType tipo, String titulo, String encabezado, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(encabezado);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}