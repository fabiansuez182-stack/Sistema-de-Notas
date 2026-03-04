package clases;

import java.util.HashMap;
import entidades.Estudiante;

public class ModeloDatos {
    HashMap<String, Estudiante> estudiantesMap;

    public ModeloDatos() {
        estudiantesMap = new HashMap<String, Estudiante>();
    }

    public String registrarEstudiante(Estudiante est) {
        if (!estudiantesMap.containsKey(est.getDocumento())) {
            estudiantesMap.put(est.getDocumento(), est);
            return "ok";
        } else {
            return "El documento ya se encuentra registrado.";
        }
    }

    public Estudiante consultaEstudiante(String documento) {
        return estudiantesMap.getOrDefault(documento, null);
    }

    public boolean eliminarEstudiante(String documento) {
        if (estudiantesMap.containsKey(documento)) {
            estudiantesMap.remove(documento);
            return true;
        }
        return false;
    }

    public String actualizarEstudiante(Estudiante est) {
        if (estudiantesMap.containsKey(est.getDocumento())) {
            estudiantesMap.put(est.getDocumento(), est);
            return "Estudiante actualizado correctamente.";
        }
        return "No se encontró un estudiante con ese documento para actualizar.";
    }

    public String imprimirListaEstudiantes() {
        if (estudiantesMap.isEmpty()) return "No hay estudiantes registrados.";
        
        String msj = "DATOS ESTUDIANTES\n----------------------\n";
        for (Estudiante estudiante : estudiantesMap.values()) {
            msj += "Doc: " + estudiante.getDocumento() + " | Nombre: " + estudiante.getNombre() + " | Materia: " + estudiante.getMateria() + "\n";
            msj += "Notas: [" + estudiante.getNota1() + ", " + estudiante.getNota2() + ", " + estudiante.getNota3() + "] -> Promedio: " + estudiante.getPromedio() + "\n\n";
        }
        return msj;
    }
}