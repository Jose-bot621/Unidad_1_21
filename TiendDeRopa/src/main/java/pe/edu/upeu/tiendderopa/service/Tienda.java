package pe.edu.upeu.tiendderopa.service;

import pe.edu.upeu.tiendderopa.model.Producto;
import pe.edu.upeu.tiendderopa.repository.Inventario;
import pe.edu.upeu.tiendderopa.exception.ProductoNoEncontradoException;
import java.util.List;

public class Tienda {
    private String nombre;
    private Inventario inventario = new Inventario();

    public Tienda(String nombre) { this.nombre = nombre; }
    public String getNombre() { return nombre; }

    public void registrarProducto(Producto p) {
        inventario.agregar(p);
        System.out.println(" Agregado: " + p.getNombre());
    }

    public List<Producto> obtenerInventario() { return inventario.listarTodos(); }
    public Producto buscarProducto(String nombre) { return (Producto) inventario.buscar(nombre); }

    public boolean retirarProducto(String nombre) {
        try { inventario.eliminar(nombre); return true; }
        catch (ProductoNoEncontradoException e) { System.out.println(e.getMessage()); return false; }
    }

    public double getValorTotalInventario() { return inventario.calcularValorTotal(); }
}