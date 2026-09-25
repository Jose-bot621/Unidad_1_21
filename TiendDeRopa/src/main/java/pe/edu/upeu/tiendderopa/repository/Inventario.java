package pe.edu.upeu.tiendderopa.repository;

import pe.edu.upeu.tiendderopa.interfaces.Gestionable;
import pe.edu.upeu.tiendderopa.model.Producto;
import pe.edu.upeu.tiendderopa.exception.ProductoNoEncontradoException;
import java.util.ArrayList;
import java.util.List;

public class Inventario implements Gestionable {
    private List<Producto> productos = new ArrayList<>();

    @Override
    public void agregar(Object elemento) {
        if (elemento instanceof Producto) {
            productos.add((Producto) elemento);
        }
    }

    @Override
    public void eliminar(String nombre) throws ProductoNoEncontradoException {
        Producto p = (Producto) buscar(nombre);
        if (p == null) throw new ProductoNoEncontradoException("No existe: " + nombre);
        productos.remove(p);
    }

    @Override
    public Object buscar(String nombre) {
        for (Producto p : productos) {
            if (p.getNombre().equalsIgnoreCase(nombre)) return p;
        }
        return null;
    }

    public List<Producto> listarTodos() { return new ArrayList<>(productos); }
    public double calcularValorTotal() {
        double total = 0;
        for (Producto p : productos) total += p.getPrecio() * p.getStock();
        return total;
    }
}