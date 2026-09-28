package pe.edu.upeu.tiendderopa.model;

import javafx.beans.property.*;
import pe.edu.upeu.tiendderopa.enums.EstadoProducto;
import pe.edu.upeu.tiendderopa.enums.Talla;

public class Producto {
    private final StringProperty tipo;
    private final StringProperty nombre;
    private final ObjectProperty<Categoria> categoria;
    private final DoubleProperty precio;
    private final IntegerProperty stock;
    private final ObjectProperty<Talla> talla;
    private final StringProperty marca;
    private EstadoProducto estado;

    public Producto(String tipo, String nombre, Categoria categoria,
                    double precio, int stock, Talla talla, String marca) {
        this.tipo = new SimpleStringProperty(tipo);
        this.nombre = new SimpleStringProperty(nombre);
        this.categoria = new SimpleObjectProperty<>(categoria);
        this.precio = new SimpleDoubleProperty(precio);
        this.stock = new SimpleIntegerProperty(stock);
        this.talla = new SimpleObjectProperty<>(talla);
        this.marca = new SimpleStringProperty(marca);
        this.estado = stock > 0 ? EstadoProducto.DISPONIBLE : EstadoProducto.AGOTADO;
    }

    public String getTipo() { return tipo.get(); }
    public void setTipo(String valor) { tipo.set(valor); }
    public StringProperty tipoProperty() { return tipo; }

    public String getNombre() { return nombre.get(); }
    public void setNombre(String valor) { nombre.set(valor); }
    public StringProperty nombreProperty() { return nombre; }

    public Categoria getCategoria() { return categoria.get(); }
    public void setCategoria(Categoria valor) { categoria.set(valor); }
    public ObjectProperty<Categoria> categoriaProperty() { return categoria; }

    public double getPrecio() { return precio.get(); }
    public void setPrecio(double valor) { precio.set(valor); }
    public DoubleProperty precioProperty() { return precio; }

    public int getStock() { return stock.get(); }
    public void setStock(int valor) {
        stock.set(valor);
        this.estado = valor > 0 ? EstadoProducto.DISPONIBLE : EstadoProducto.AGOTADO;
    }
    public IntegerProperty stockProperty() { return stock; }

    public Talla getTalla() { return talla.get(); }
    public void setTalla(Talla valor) { talla.set(valor); }
    public ObjectProperty<Talla> tallaProperty() { return talla; }

    public String getMarca() { return marca.get(); }
    public void setMarca(String valor) { marca.set(valor); }
    public StringProperty marcaProperty() { return marca; }

    public EstadoProducto getEstado() { return estado; }
}
