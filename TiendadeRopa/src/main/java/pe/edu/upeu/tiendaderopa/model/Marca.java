package pe.edu.upeu.tiendaderopa.model;

public class Marca {

    private int id;
    private String nombre;

    public Marca() {
    }

    public Marca(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}