package produ.uam.suiteregistro.models;

public class Estudiante {
    private String nombres;
    private String apellidos;
    private String carrera;
    private String carnet;
    private String correo;

    public Estudiante(String nombres, String apellidos, String carrera, String carnet, String correo) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.carrera = carrera;
        this.carnet = carnet;
        this.correo = correo;
    }

    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getCarrera() { return carrera; }
    public void setCarrera(String carrera) { this.carrera = carrera; }

    public String getCarnet() { return carnet; }
    public void setCarnet(String carnet) { this.carnet = carnet; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    @Override
    public String toString() {
        return "----------------------------------------\n" +
                "Carnet:    " + carnet + "\n" +
                "Estudiante: " + nombres + " " + apellidos + "\n" +
                "Carrera:   " + carrera + "\n" +
                "Correo:    " + correo + "\n" +
                "----------------------------------------\n";
    }
}