package produ.uam.suiteregistro.repository;

import produ.uam.suiteregistro.models.Estudiante;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EstudianteRepository {
    private final List<Estudiante> listaEstudiantes;

    public EstudianteRepository() {
        this.listaEstudiantes = new ArrayList<>();
    }

    public void guardar(Estudiante estudiante) {
        listaEstudiantes.add(estudiante);
    }

    public List<Estudiante> listarTodos() {
        return Collections.unmodifiableList(listaEstudiantes);
    }

    public boolean existeCarnet(String carnet) {
        return listaEstudiantes.stream()
                .anyMatch(e -> e.getCarnet().equalsIgnoreCase(carnet));
    }
}