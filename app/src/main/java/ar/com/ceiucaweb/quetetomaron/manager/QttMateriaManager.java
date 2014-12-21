package ar.com.ceiucaweb.quetetomaron.manager;

import java.util.List;

import ar.com.ceiucaweb.quetetomaron.entidad.Carrera;
import ar.com.ceiucaweb.quetetomaron.entidad.Materia;

/**
 * Provee operaciones de tipo CRUD sobre la entidad {@link ar.com.ceiucaweb.quetetomaron.entidad.Materia}
 * Creado por juanmougan@gmail.com el 29/11/14.
 */
public interface QttMateriaManager {

    public List<Materia> fetchAllMaterias();

    public List<Materia> fetchMateriasDeCarrera(Carrera carrera);

    public List<Materia> fetchMateriasDeCarreraPorNombre(CharSequence nombreCarrera);

}
