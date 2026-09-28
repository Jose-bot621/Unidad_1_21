package pe.edu.upeu.tiendaderopa.repository;

import pe.edu.upeu.tiendaderopa.interfaces.CrudRepository;
import pe.edu.upeu.tiendaderopa.model.Producto;

import java.util.ArrayList;
import java.util.List;

public class ProductoRepository implements CrudRepository<Producto> {

    private final List<Producto> productos = new ArrayList<>();

    private int contadorId = 1;

    @Override
    public Producto guardar(Producto producto) {

        producto.setId(contadorId++);

        productos.add(producto);

        return producto;
    }

    @Override
    public Producto actualizar(Producto producto) {

        for (int i = 0; i < productos.size(); i++) {

            if (productos.get(i).getId() == producto.getId()) {

                productos.set(i, producto);

                return producto;
            }
        }

        return null;
    }

    @Override
    public boolean eliminar(int id) {

        return productos.removeIf(
                producto -> producto.getId() == id
        );
    }

    @Override
    public Producto buscarPorId(int id) {

        for (Producto producto : productos) {

            if (producto.getId() == id) {
                return producto;
            }
        }

        return null;
    }

    @Override
    public List<Producto> listar() {

        return new ArrayList<>(productos);
    }
}