package ar.com.ceiucaweb.quetetomaron.entidad;

/**
 * Representa una Materia de una Carrera dentro de la facultad.
 * Creado por juanmougan@gmail.com el 29/11/14.
 */
public class Materia {

    private long id;

    private String nombre;

    private Carrera carrera;

    public Materia() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Carrera getCarrera() {
        return this.carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    @Override
    public String toString() {
        return this.getNombre();
    }
}
