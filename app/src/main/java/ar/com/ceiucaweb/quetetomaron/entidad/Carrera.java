package ar.com.ceiucaweb.quetetomaron.entidad;

/**
 * Representa una Materia de una Carrera dentro de la facultad.
 * Creado por juanmougan@gmail.com el 29/11/14.
 */
public class Carrera {

    private long id;

    private String nombre;

    public Carrera() {
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

    @Override
    public String toString() {
        return this.getNombre();
    }
}
