package produ.uam.suiteregistro.repository;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import produ.uam.suiteregistro.models.Pelicula;

public class PeliculaRepository {
    private final ObservableList<Pelicula> catalogo;

    public PeliculaRepository() {
        this.catalogo = FXCollections.observableArrayList();
        cargarDatosPorDefecto();
    }

    private void cargarDatosPorDefecto() {
        catalogo.add(new Pelicula("Interstellar", "Christopher Nolan", "Ciencia Ficción", "2014", "169 min"));
        catalogo.add(new Pelicula("Inception", "Christopher Nolan", "Acción / Suspenso", "2010", "148 min"));
        catalogo.add(new Pelicula("El Viaje de Chihiro", "Hayao Miyazaki", "Animación", "2001", "125 min"));
        catalogo.add(new Pelicula("The Dark Knight", "Christopher Nolan", "Acción", "2008", "152 min"));
    }

    public ObservableList<Pelicula> obtenerCatalogo() {
        return catalogo;
    }

    public void agregarPelicula(Pelicula pelicula) {
        catalogo.add(pelicula);
    }
}
