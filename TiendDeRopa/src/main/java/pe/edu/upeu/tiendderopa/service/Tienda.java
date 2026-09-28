package pe.edu.upeu.tiendderopa.service;

import pe.edu.upeu.tiendderopa.model.Producto;
import pe.edu.upeu.tiendderopa.repository.Inventario;
import pe.edu.upeu.tiendderopa.exception.ProductoNoEncontradoException;
import java.util.List;

public class Tienda {
    private String nombre;
    private Inventario inventario;

    public Tienda(String nombre) {
        this.nombre = nombre;
        this.inventario = new Inventario();
    }

    public String getNombre() {
        return nombre;
    }

    public void registrarProducto(Producto p) {
        inventario.agregar(p);
        System.out.println("Agregado: " + p.getNombre());
    }

    public List<Producto> obtenerInventario() {
        return inventario.listarTodos();
    }

    public Producto buscarProducto(String nombre) {
        return inventario.buscar(nombre);
    }

    public boolean retirarProducto(String nombre) {
        try {
            inventario.eliminar(nombre);
            return true;
        } catch (ProductoNoEncontradoException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public double getValorTotalInventario() {
        return inventario.calcularValorTotal();
    }
}
