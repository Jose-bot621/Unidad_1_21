package pe.edu.upeu.tiendaderopa.enums;

public enum Categoria {

    POLOS("Polos"),
    PANTALONES("Pantalones"),
    CASACAS("Casacas"),
    VESTIDOS("Vestidos"),
    ZAPATILLAS("Zapatillas");

    private final String descripcion;

    Categoria(String descripcion) {
        this.descripcion = descripcion;
    }


    @Override
    public String toString() {
        return descripcion;
    }
}