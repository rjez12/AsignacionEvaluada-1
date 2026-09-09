package produ.uam.suiteregistro.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class PrincipalController {

    @FXML
    private AnchorPane rootPane; // Debe coincidir con el fx:id de tu AnchorPane en el FXML

    @FXML
    private void initialize() {
        // Crear ContextMenu programáticamente para permitir clic derecho en toda la ventana
        ContextMenu contextMenu = new ContextMenu();

        MenuItem itemEstudiantes = new MenuItem("Ir a Registro de Estudiantes");
        itemEstudiantes.setOnAction(this::onAbrirEstudiantes);

        MenuItem itemPeliculas = new MenuItem("Ir a Registro de Películas");
        itemPeliculas.setOnAction(this::onAbrirPeliculas);

        MenuItem itemDev = new MenuItem("Acerca del Desarrollador");
        itemDev.setOnAction(this::onMostrarDesarrollador);

        MenuItem itemSalir = new MenuItem("Salir");
        itemSalir.setOnAction(this::onSalir);

        contextMenu.getItems().addAll(
                itemEstudiantes,
                itemPeliculas,
                new SeparatorMenuItem(),
                itemDev,
                itemSalir
        );

        // Se activa al hacer clic derecho en cualquier punto del AnchorPane
        if (rootPane != null) {
            rootPane.setOnContextMenuRequested(event -> {
                contextMenu.show(rootPane, event.getScreenX(), event.getScreenY());
            });
        }
    }

    @FXML
    private void onAbrirEstudiantes(ActionEvent event) {
        cargarVentana("registro-estudiantes.fxml", "Módulo de Registro de Estudiantes");
    }

    @FXML
    private void onAbrirPeliculas(ActionEvent event) {
        cargarVentana("registro-peliculas.fxml", "Módulo de Registro de Películas");
    }

    @FXML
    private void onMostrarDesarrollador(ActionEvent event) {
        Alert alerta = new Alert(AlertType.INFORMATION);
        alerta.setTitle("Información del Desarrollador");
        alerta.setHeaderText("Proyecto: Suite de Registros");
        alerta.setContentText(
                "Desarrollador: Jesser Rodriguez\n"
                        + "Institución: Universidad Americana (UAM)\n"
                        + "Carrera: Ingeniería en Sistemas\n"
                        + "Asignatura: Programación Visual / JavaFX\n"
                        + "Versión: 1.0"
        );
        alerta.showAndWait();
    }

    @FXML
    private void onSalir(ActionEvent event) {
        Platform.exit();
    }

    private void cargarVentana(String nombreFxml, String titulo) {
        try {
            // Se usa el ClassLoader o la clase PrincipalApplication como ancla
            java.net.URL url = produ.uam.suiteregistro.PrincipalApplication.class.getResource(nombreFxml);

            if (url == null) {
                // Alternativa de ruta absoluta si no lo encuentra relativo
                url = getClass().getResource("/produ/uam/suiteregistro/" + nombreFxml);
            }

            if (url == null) {
                throw new IOException("No se encontró el archivo FXML en la ruta especificada.");
            }

            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle(titulo);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            Alert alertError = new Alert(AlertType.ERROR);
            alertError.setTitle("Error de Navegación");
            alertError.setHeaderText("No se pudo cargar la vista");
            alertError.setContentText("Error al intentar abrir el archivo: " + nombreFxml + "\n\nDetalle: " + e.getMessage());
            alertError.showAndWait();
            e.printStackTrace(); // Revisa la consola inferior de Run para ver la causa exacta
        }
    }
}