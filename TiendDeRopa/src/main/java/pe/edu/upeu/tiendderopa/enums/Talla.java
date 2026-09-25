package pe.edu.upeu.tiendderopa.enums;

public enum Talla {
    XS("Extra Chica"),
    S("Chica"),
    M("Mediana"),
    L("Grande"),
    XL("Extra Grande"),
    XXL("Doble Extra Grande");

    private final String descripcion;
    Talla(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getDescripcion() { return descripcion; }
}