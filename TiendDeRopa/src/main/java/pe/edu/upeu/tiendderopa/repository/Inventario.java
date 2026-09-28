package pe.edu.upeu.tiendderopa.repository;

import pe.edu.upeu.tiendderopa.exception.ProductoNoEncontradoException;
import pe.edu.upeu.tiendderopa.model.Producto;

import java.util.ArrayList;
import java.util.List;

public class Inventario {
    private List<Producto> productos;

    public Inventario() {
        productos = new ArrayList<>();
    }

    public void agregar(Producto p) {
        productos.add(p);
    }

    public List<Producto> listarTodos() {
        return new ArrayList<>(productos);
    }

    public Producto buscar(String nombre) {
        for (Producto p : productos) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                return p;
            }
        }
        return null;
    }

    public void eliminar(String nombre) throws ProductoNoEncontradoException {
        Producto p = buscar(nombre);
        if (p == null) {
            throw new ProductoNoEncontradoException("Producto no encontrado: " + nombre);
        }
        productos.remove(p);
    }

    public double calcularValorTotal() {
        double total = 0;
        for (Producto p : productos) {
            total += p.getPrecio() * p.getStock();
        }
        return total;
    }
}
