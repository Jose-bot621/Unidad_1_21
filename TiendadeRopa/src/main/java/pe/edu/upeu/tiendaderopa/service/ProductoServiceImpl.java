package pe.edu.upeu.tiendaderopa.service;

import pe.edu.upeu.tiendaderopa.exception.ProductoException;
import pe.edu.upeu.tiendaderopa.interfaces.ProductoService;
import pe.edu.upeu.tiendaderopa.model.Producto;
import pe.edu.upeu.tiendaderopa.repository.ProductoRepository;

import java.util.List;

public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository repository;

    public ProductoServiceImpl() {
        repository = new ProductoRepository();
    }

    @Override
    public Producto guardar(Producto producto) {

        validar(producto);

        return repository.guardar(producto);
    }

    @Override
    public Producto actualizar(Producto producto) {

        validar(producto);

        if (repository.buscarPorId(producto.getId()) == null) {

            throw new ProductoException(
                    "El producto no existe."
            );
        }

        return repository.actualizar(producto);
    }

    @Override
    public boolean eliminar(int id) {

        if (repository.buscarPorId(id) == null) {

            throw new ProductoException(
                    "El producto no existe."
            );
        }

        return repository.eliminar(id);
    }

    @Override
    public Producto buscarPorId(int id) {

        return repository.buscarPorId(id);
    }

    @Override
    public List<Producto> listar() {

        return repository.listar();
    }

    private void validar(Producto producto) {

        if (producto == null) {

            throw new ProductoException(
                    "El producto no puede estar vacío."
            );
        }

        if (producto.getNombre() == null ||
                producto.getNombre().trim().isEmpty()) {

            throw new ProductoException(
                    "Ingrese el nombre del producto."
            );
        }

        if (producto.getPrecio() <= 0) {

            throw new ProductoException(
                    "El precio debe ser mayor que 0."
            );
        }

        if (producto.getStock() < 0) {

            throw new ProductoException(
                    "El stock no puede ser negativo."
            );
        }

        if (producto.getMarca() == null) {

            throw new ProductoException(
                    "Seleccione una marca."
            );
        }

        if (producto.getPrenda() == null) {

            throw new ProductoException(
                    "Seleccione una prenda."
            );
        }

        if (producto.getCategoria() == null) {

            throw new ProductoException(
                    "Seleccione una categoría."
            );
        }

        if (producto.getTalla() == null) {

            throw new ProductoException(
                    "Seleccione una talla."
            );
        }
    }
}