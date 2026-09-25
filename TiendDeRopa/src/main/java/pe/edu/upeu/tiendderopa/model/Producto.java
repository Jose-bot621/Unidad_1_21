package pe.edu.upeu.tiendderopa.model;

import javafx.beans.property.*;
import pe.edu.upeu.tiendderopa.enums.EstadoProducto;
import pe.edu.upeu.tiendderopa.enums.Talla;
import pe.edu.upeu.tiendderopa.interfaces.Mostrable;

public class Producto implements Mostrable {
    private final StringProperty tipo;
    private final StringProperty nombre;
    private final ObjectProperty<Categoria> categoria;
    private final DoubleProperty precio;
    private final IntegerProperty stock;
    private final ObjectProperty<Talla> talla;
    private final StringProperty marca;
    private final StringProperty unidadMedida;
    private EstadoProducto estado;

    public Producto(String tipo, String nombre, Categoria categoria, double precio, int stock, Talla talla, String marca, String unidadMedida) {
        this.tipo = new SimpleStringProperty(tipo);
        this.nombre = new SimpleStringProperty(nombre);
        this.categoria = new SimpleObjectProperty<>(categoria);
        this.precio = new SimpleDoubleProperty(precio);
        this.stock = new SimpleIntegerProperty(stock);
        this.talla = new SimpleObjectProperty<>(talla);
        this.marca = new SimpleStringProperty(marca);
        this.unidadMedida = new SimpleStringProperty(unidadMedida);
        this.estado = stock > 0 ? EstadoProducto.DISPONIBLE : EstadoProducto.AGOTADO;
    }

    public String getNombre() { return nombre.get(); }
    public Categoria getCategoria() { return categoria.get(); }
    public double getPrecio() { return precio.get(); }
    public int getStock() { return stock.get(); }
    public Talla getTalla() { return talla.get(); }
    public EstadoProducto getEstado() { return estado; }

    public StringProperty tipoProperty() { return tipo; }
    public StringProperty nombreProperty() { return nombre; }
    public StringProperty categoriaProperty() { return new SimpleStringProperty(categoria.get().getNombre()); }
    public DoubleProperty precioProperty() { return precio; }
    public IntegerProperty stockProperty() { return stock; }
    public StringProperty tallaProperty() { return new SimpleStringProperty(talla.get().name()); }
    public StringProperty marcaProperty() { return marca; }
    public StringProperty unidadMedidaProperty() { return unidadMedida; }

    public void actualizarStock(int cantidad) {
        this.stock.set(this.stock.get() + cantidad);
        this.estado = this.stock.get() > 0 ? EstadoProducto.DISPONIBLE : EstadoProducto.AGOTADO;
    }

    @Override
    public String mostrarInformacion() {
        return String.format("%-15s | %-12s | %-4s | S/ %6.2f | Stock: %-3d | %s",
                nombre.get(), categoria.get().getNombre(), talla.get(), precio.get(), stock.get(), estado);
    }
}
