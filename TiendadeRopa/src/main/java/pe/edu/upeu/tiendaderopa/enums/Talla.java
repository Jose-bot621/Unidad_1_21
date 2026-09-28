package pe.edu.upeu.tiendaderopa.enums;

public enum Talla {

    XS("Extra pequeña"),
    S("Pequeña"),
    M("Mediana"),
    L("Grande"),
    XL("Extra grande"),
    XXL("Doble extra grande");

    private final String descripcion;

    Talla(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return name();
    }
}