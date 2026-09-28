package pe.edu.upeu.tiendaderopa.interfaces;

import java.util.List;

public interface CrudRepository<T> {

    T guardar(T objeto);

    T actualizar(T objeto);

    boolean eliminar(int id);

    T buscarPorId(int id);

    List<T> listar();
}