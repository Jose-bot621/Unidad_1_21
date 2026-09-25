package pe.edu.upeu.tiendderopa.interfaces;

import pe.edu.upeu.tiendderopa.exception.ProductoNoEncontradoException;

public interface Gestionable {
    void agregar(Object elemento);
    void eliminar(String nombre) throws ProductoNoEncontradoException;
    Object buscar(String nombre);
}