package pe.edu.upeu.tiendderopa.model;

import pe.edu.upeu.tiendderopa.interfaces.Mostrable;

public class Categoria implements Mostrable {
    private String nombre;
    private String descripcion;

    public Categoria(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }

    @Override
    public String mostrarInformacion() {
        return "Categoría: " + nombre + " - " + descripcion;
    }
}