package ar.com.ceiucaweb.quetetomaron.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.com.ceiucaweb.quetetomaron.entidad.Carrera;
import ar.com.ceiucaweb.quetetomaron.entidad.Materia;

/**
 * Mock de la implementación. Los nombres están harcodeados!
 * Created by juanmougan@gmail.com on 30/11/14.
 */
public final class MateriaMockManager implements QttMateriaManager {

    private static List<Materia> materias = new ArrayList<Materia>();
    private static QttCarreraManager carreraManager = CarreraMockManager.newInstance();
    private static MateriaMockManager instance = null;

    private MateriaMockManager() {
        // Estos id's están "atados" a los de Carreras, OJO!
        crearMaterias();
    }

    private void crearMaterias() {

        Materia materia1 = new Materia();
        materia1.setId(1);
        materia1.setNombre("Programación I");
        materia1.setCarrera(carreraManager.findCarreraById(3));
        materias.add(materia1);

        Materia materia2 = new Materia();
        materia2.setId(2);
        materia2.setNombre("Ing. de Software II");
        materia2.setCarrera(carreraManager.findCarreraById(3));
        materias.add(materia2);

        Materia materia3 = new Materia();
        materia3.setId(3);
        materia3.setNombre("Modelos de Transportes de Contaminantes");
        materia3.setCarrera(carreraManager.findCarreraById(1));
        materias.add(materia3);

    }

    public static MateriaMockManager newInstance() {
        if (instance == null)
            instance = new MateriaMockManager();
        return instance;
    }

    @Override
    public List<Materia> fetchAllMaterias() {
        return materias;
    }
}
