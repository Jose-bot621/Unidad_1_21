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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Prenda getPrenda() {
        return prenda;
    }

    public void setPrenda(Prenda prenda) {
        this.prenda = prenda;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Talla getTalla() {
        return talla;
    }

    public void setTalla(Talla talla) {
        this.talla = talla;
    }

    @Override
    public String toString() {
        return nombre;
    }
}