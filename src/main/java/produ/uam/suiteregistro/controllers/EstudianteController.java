package produ.uam.suiteregistro.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import produ.uam.suiteregistro.models.Estudiante;
import produ.uam.suiteregistro.repository.EstudianteRepository;

import java.util.List;

public class EstudianteController {

    @FXML
    private TextField txtNombres;
    @FXML
    private TextField txtApellidos;
    @FXML
    private TextField txtCarrera;
    @FXML
    private TextField txtCarnet;
    @FXML
    private TextField txtCorreo;

    @FXML
    private TextArea txtAreaMostrar;

    private final EstudianteRepository estudianteRepository = new EstudianteRepository();

    @FXML
    private void onRegistrar(ActionEvent event) {
        String nombres = txtNombres.getText().trim();
        String apellidos = txtApellidos.getText().trim();
        String carrera = txtCarrera.getText().trim();
        String carnet = txtCarnet.getText().trim();
        String correo = txtCorreo.getText().trim();

        if (nombres.isEmpty() || apellidos.isEmpty() || carrera.isEmpty() || carnet.isEmpty() || correo.isEmpty()) {
            mostrarAlerta(AlertType.WARNING, "Campos Vacíos", "Debe completar todos los campos del formulario antes de registrar.");
            return;
        }

        if (estudianteRepository.existeCarnet(carnet)) {
            mostrarAlerta(AlertType.ERROR, "Registro Duplicado", "Ya existe un estudiante registrado con el carnet: " + carnet);
            return;
        }

        Estudiante nuevoEstudiante = new Estudiante(nombres, apellidos, carrera, carnet, correo);
        estudianteRepository.guardar(nuevoEstudiante);

        txtAreaMostrar.appendText("Bienvenido!:\n" + nuevoEstudiante.toString() + "\n");

        mostrarAlerta(AlertType.INFORMATION, "Éxito", "Estudiante guardado correctamente.");
        limpiarFormulario();
    }

    @FXML
    private void onLimpiar(ActionEvent event) {
        limpiarFormulario();
    }

    @FXML
    private void onMostrar(ActionEvent event) {
        List<Estudiante> estudiantes = estudianteRepository.listarTodos();

        if (estudiantes.isEmpty()) {
            txtAreaMostrar.setText("No hay registros almacenados actualmente en el sistema.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Lista general de estudiantes\n\n");
        for (Estudiante est : estudiantes) {
            sb.append(est.toString());
        }
        txtAreaMostrar.setText(sb.toString());
    }

    private void limpiarFormulario() {
        txtNombres.clear();
        txtApellidos.clear();
        txtCarrera.clear();
        txtCarnet.clear();
        txtCorreo.clear();
        txtNombres.requestFocus();
        txtAreaMostrar.clear();

    }

    private void mostrarAlerta(AlertType tipo, String titulo, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }


}