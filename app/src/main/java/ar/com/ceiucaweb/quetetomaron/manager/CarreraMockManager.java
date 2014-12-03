package ar.com.ceiucaweb.quetetomaron.manager;

import java.util.ArrayList;
import java.util.List;

import ar.com.ceiucaweb.quetetomaron.entidad.Carrera;

/**
 * Mock de la implementación. Los nombres están harcodeados!
 * Created by juanmougan@gmail.com on 29/11/14.
 */
public final class CarreraMockManager implements QttCarreraManager {

    private static String[] nombreCarreras = {"Materias Comunes", "Ambiental", "Electrónica", "Informática"};
    private static List<Carrera> carreras = new ArrayList<Carrera>();
    private static CarreraMockManager instance = null;

    private CarreraMockManager() {
        int i = 0;
        for (String unaCarrera : nombreCarreras) {
            Carrera carrera = new Carrera();
            carrera.setNombre(unaCarrera);
            carrera.setId(++i);
            carreras.add(carrera);
        }
    }

    public static CarreraMockManager newInstance() {
        if (instance == null)
            instance = new CarreraMockManager();
        return instance;
    }

    @Override
    public List<Carrera> fetchAllCarreras() {
        return carreras;
    }

    @Override
    public Carrera findCarreraById(long id) {
        for (Carrera carrera : CarreraMockManager.carreras) {
            if (carrera.getId() == id)
                return carrera;
        }
        return null;
    }

}
