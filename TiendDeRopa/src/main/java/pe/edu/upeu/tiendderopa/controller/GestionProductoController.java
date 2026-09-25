package pe.edu.upeu.tiendderopa.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import pe.edu.upeu.tiendderopa.enums.Talla;
import pe.edu.upeu.tiendderopa.model.Categoria;
import pe.edu.upeu.tiendderopa.model.Producto;
import pe.edu.upeu.tiendderopa.service.Tienda;

public class GestionProductoController {

    @FXML private TextField txtTipoProducto;
    @FXML private TextField txtNombreProducto;
    @FXML private TextField txtPrecio;
    @FXML private TextField txtStock;
    @FXML private TextField txtCategoria;
    @FXML private TextField txtMarca;
    @FXML private ComboBox<Talla> cbxTalla;
    @FXML private TextField txtUnidadMedida;
    @FXML private TextField txtBuscar;

    @FXML private TableView<Producto> tablaProductos;
    @FXML private TableColumn<Producto, String> colTipo;
    @FXML private TableColumn<Producto, String> colNombre;
    @FXML private TableColumn<Producto, Double> colPrecio;
    @FXML private TableColumn<Producto, Integer> colStock;
    @FXML private TableColumn<Producto, String> colCategoria;
    @FXML private TableColumn<Producto, String> colTalla;
    @FXML private TableColumn<Producto, String> colMarca;
    @FXML private TableColumn<Producto, String> colUnidad;

    private Tienda tienda;
    private ObservableList<Producto> listaProductos;

    @FXML
    public void initialize() {
        tienda = new Tienda("Moda y Estilo");
        listaProductos = FXCollections.observableArrayList();
        cbxTalla.setItems(FXCollections.observableArrayList(Talla.values()));

        colTipo.setCellValueFactory(cell -> cell.getValue().tipoProperty());
        colNombre.setCellValueFactory(cell -> cell.getValue().nombreProperty());
        colPrecio.setCellValueFactory(cell -> cell.getValue().precioProperty().asObject());
        colStock.setCellValueFactory(cell -> cell.getValue().stockProperty().asObject());
        colCategoria.setCellValueFactory(cell -> cell.getValue().categoriaProperty());
        colTalla.setCellValueFactory(cell -> cell.getValue().tallaProperty());
        colMarca.setCellValueFactory(cell -> cell.getValue().marcaProperty());
        colUnidad.setCellValueFactory(cell -> cell.getValue().unidadMedidaProperty());

        tablaProductos.setItems(listaProductos);
    }

    @FXML
    void guardarProducto(ActionEvent e) {
        try {
            String tipo = txtTipoProducto.getText().trim();
            String nombre = txtNombreProducto.getText().trim();
            double precio = Double.parseDouble(txtPrecio.getText().trim());
            int stock = Integer.parseInt(txtStock.getText().trim());
            String cat = txtCategoria.getText().trim();
            String marca = txtMarca.getText().trim();
            Talla talla = cbxTalla.getValue();
            String unidad = txtUnidadMedida.getText().trim();

            if (nombre.isEmpty() || talla == null) {
                alert("⚠️ Nombre y Talla son obligatorios"); return;
            }

            Producto p = new Producto(tipo, nombre, new Categoria(cat, ""), precio, stock, talla, marca, unidad);
            tienda.registrarProducto(p);
            listaProductos.add(p);
            limpiar();
            alert(" Guardado correctamente");
        } catch (NumberFormatException ex) {
            alert(" Precio y Stock deben ser números");
        }
    }

    @FXML void cancelar(ActionEvent e) { limpiar(); }

    @FXML void buscarProducto(ActionEvent e) {
        String nom = txtBuscar.getText().trim();
        listaProductos.clear();
        if (nom.isEmpty()) listaProductos.addAll(tienda.obtenerInventario());
        else {
            Producto enc = tienda.buscarProducto(nom);
            if (enc != null) listaProductos.add(enc);
            else alert(" No encontrado");
        }
    }

    private void limpiar() {
        txtTipoProducto.clear(); txtNombreProducto.clear(); txtPrecio.clear();
        txtStock.clear(); txtCategoria.clear(); txtMarca.clear(); txtUnidadMedida.clear();
        cbxTalla.setValue(null);
    }

    private void alert(String msg) {
        new Alert(Alert.AlertType.INFORMATION, msg).showAndWait();
    }
}