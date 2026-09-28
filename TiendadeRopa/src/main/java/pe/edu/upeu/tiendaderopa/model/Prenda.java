package pe.edu.upeu.tiendaderopa.model;

public class Prenda {

    private int id;
    private String nombre;

    public Prenda() {
    }

    public Prenda(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }



    @Override
    public String toString() {
        return nombre;
    }
}