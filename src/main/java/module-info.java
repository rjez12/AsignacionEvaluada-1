module produ.uam.suiteregistro {
    requires javafx.controls;
    requires javafx.fxml;

    // Paquete raíz
    opens produ.uam.suiteregistro to javafx.fxml;
    exports produ.uam.suiteregistro;

    // Controladores (necesario para EstudianteController, PeliculaController, etc.)
    opens produ.uam.suiteregistro.controllers to javafx.fxml;
    exports produ.uam.suiteregistro.controllers;

    // Modelos (necesario para que TableView acceda a las propiedades de Pelicula/Estudiante)
    opens produ.uam.suiteregistro.models to javafx.base;
    exports produ.uam.suiteregistro.models;
}