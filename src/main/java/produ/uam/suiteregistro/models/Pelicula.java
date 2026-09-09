package produ.uam.suiteregistro.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Pelicula {
    private final StringProperty titulo;
    private final StringProperty director;
    private final StringProperty genero;
    private final StringProperty anioEstreno;
    private final StringProperty duracion;

    public Pelicula(String titulo, String director, String genero, String anioEstreno, String duracion) {
        this.titulo = new SimpleStringProperty(titulo);
        this.director = new SimpleStringProperty(director);
        this.genero = new SimpleStringProperty(genero);
        this.anioEstreno = new SimpleStringProperty(anioEstreno);
        this.duracion = new SimpleStringProperty(duracion);
    }

    public String getTitulo() { return titulo.get(); }
    public StringProperty tituloProperty() { return titulo; }
    public void setTitulo(String titulo) { this.titulo.set(titulo); }

    public String getDirector() { return director.get(); }
    public StringProperty directorProperty() { return director; }
    public void setDirector(String director) { this.director.set(director); }

    public String getGenero() { return genero.get(); }
    public StringProperty generoProperty() { return genero; }
    public void setGenero(String genero) { this.genero.set(genero); }

    public String getAnioEstreno() { return anioEstreno.get(); }
    public StringProperty anioEstrenoProperty() { return anioEstreno; }
    public void setAnioEstreno(String anioEstreno) { this.anioEstreno.set(anioEstreno); }

    public String getDuracion() { return duracion.get(); }
    public StringProperty duracionProperty() { return duracion; }
    public void setDuracion(String duracion) { this.duracion.set(duracion); }
}
