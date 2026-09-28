package pe.edu.upeu.tiendaderopa.model;

import pe.edu.upeu.tiendaderopa.enums.Categoria;
import pe.edu.upeu.tiendaderopa.enums.Talla;

public class Producto {

    private int id;
    private String nombre;
    private double precio;
    private int stock;

    private Marca marca;
    private Prenda prenda;

    private Categoria categoria;
    private Talla talla;

    public Producto() {
    }

    public Producto(int id,
                    String nombre,
                    double precio,
                    int stock,
                    Marca marca,
                    Prenda prenda,
                    Categoria categoria,
                    Talla talla) {

        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.marca = marca;
        this.prenda = prenda;
        this.categoria = categoria;
        this.talla = talla;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    public Marca getMarca() {
        return marca;
    }

    public Prenda getPrenda() {
        return prenda;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Talla getTalla() {
        return talla;
    }

    @Override
    public String toString() {
        return nombre;
    }
}