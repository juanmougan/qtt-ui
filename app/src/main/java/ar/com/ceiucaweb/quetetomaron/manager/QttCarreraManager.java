package ar.com.ceiucaweb.quetetomaron.manager;

import java.util.List;

import ar.com.ceiucaweb.quetetomaron.entidad.Carrera;

/**
 * Provee operaciones de tipo CRUD sobre la entidad {@link ar.com.ceiucaweb.quetetomaron.entidad.Carrera}
 * Creado por juanmougan@gmail.com el 29/11/14.
 */
public interface QttCarreraManager {

    public List<Carrera> fetchAllCarreras();

}
