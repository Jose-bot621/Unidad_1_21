package pe.edu.upeu.tiendaderopa.interfaces;

import pe.edu.upeu.tiendaderopa.model.Producto;

import java.util.List;

public interface ProductoService {

    Producto guardar(Producto producto);

    Producto actualizar(Producto producto);

    boolean eliminar(int id);

    Producto buscarPorId(int id);

    List<Producto> listar();
}