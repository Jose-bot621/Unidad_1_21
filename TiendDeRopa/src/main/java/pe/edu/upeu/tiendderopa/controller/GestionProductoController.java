package pe.edu.upeu.tiendderopa.controller;

// =============================================
// ✅ IMPORTS COMPLETOS Y BIEN ESCRITOS
// =============================================
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ActionEvent;  // ← ESTE faltaba ✅
import pe.edu.upeu.tiendderopa.enums.Talla;
import pe.edu.upeu.tiendderopa.model.Categoria;
import pe.edu.upeu.tiendderopa.model.Producto;

public class GestionProductoController {

    @FXML private ComboBox<String> cbxTipo;
    @FXML private TextField txtNombre;
    @FXML private TextField txtPrecio;
    @FXML private TextField txtStock;
    @FXML private ComboBox<String> cbxCategoria;
    @FXML private ComboBox<Talla> cbxTalla;
    @FXML private ComboBox<String> cbxMarca;

    @FXML private TableView<Producto> tablaProductos;
    @FXML private TableColumn<Producto, String> colTipo;
    @FXML private TableColumn<Producto, String> colNombre;
    @FXML private TableColumn<Producto, Double> colPrecio;
    @FXML private TableColumn<Producto, Integer> colStock;
    @FXML private TableColumn<Producto, String> colCategoria;
    @FXML private TableColumn<Producto, String> colTalla;
    @FXML private TableColumn<Producto, String> colMarca;

    private ObservableList<Producto> listaProductos;
    private Producto productoEnEdicion;

    @FXML
    public void initialize() {
        listaProductos = FXCollections.observableArrayList();

        cbxTipo.setItems(FXCollections.observableArrayList(
                "Camiseta", "Pantalón", "Camisa", "Vestido", "Chaqueta"
        ));
        cbxCategoria.setItems(FXCollections.observableArrayList(
                "Ropa Casual", "Ropa Deportiva", "Ropa Formal"
        ));
        cbxMarca.setItems(FXCollections.observableArrayList(
                "Nike", "Adidas", "Polo", "Zara"
        ));

        // ✅ Talla con descripciones
        cbxTalla.setItems(FXCollections.observableArrayList<>(Talla.values()));
        cbxTalla.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(Talla t, boolean vacio) {
                super.updateItem(t, vacio);
                setText(t == null ? null : t.getDescripcion());
            }
        });
        cbxTalla.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Talla t, boolean vacio) {
                super.updateItem(t, vacio);
                setText(t == null ? null : t.getDescripcion());
            }
        });

        // ✅ Columnas de la tabla
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));

        colCategoria.setCellValueFactory(cellData -> {
            Categoria cat = cellData.getValue().getCategoria();
            String nombre = (cat != null) ? cat.getNombre() : "";
            return new javafx.beans.property.SimpleStringProperty(nombre);
        });

        colTalla.setCellValueFactory(cellData -> {
            Talla t = cellData.getValue().getTalla();
            String desc = (t != null) ? t.getDescripcion() : "";
            return new javafx.beans.property.SimpleStringProperty(desc);
        });

        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        tablaProductos.setItems(listaProductos);
    }

    @FXML
    void guardar(ActionEvent e) {
        try {
            String tipo = cbxTipo.getValue();
            String nombre = txtNombre.getText().trim();
            double precio = Double.parseDouble(txtPrecio.getText().trim());
            int stock = Integer.parseInt(txtStock.getText().trim());
            String catNombre = cbxCategoria.getValue();
            Talla talla = cbxTalla.getValue();
            String marca = cbxMarca.getValue();

            if (tipo == null || nombre.isEmpty() || catNombre == null
                    || talla == null || marca == null) {
                mostrarAlerta("⚠️ Completa todos los campos");
                return;
            }

            if (productoEnEdicion != null) {
                productoEnEdicion.setTipo(tipo);
                productoEnEdicion.setNombre(nombre);
                productoEnEdicion.setPrecio(precio);
                productoEnEdicion.setStock(stock);
                productoEnEdicion.setCategoria(new Categoria(catNombre, ""));
                productoEnEdicion.setTalla(talla);
                productoEnEdicion.setMarca(marca);
                mostrarAlerta("✅ Producto ACTUALIZADO");
            } else {
                Categoria cat = new Categoria(catNombre, "");
                Producto nuevo = new Producto(tipo, nombre, cat, precio, stock, talla, marca);
                listaProductos.add(nuevo);
                mostrarAlerta("✅ Producto GUARDADO");
            }
            limpiarFormulario();
            productoEnEdicion = null;
        } catch (NumberFormatException ex) {
            mostrarAlerta("❌ Precio y Stock deben ser números válidos");
        }
    }

    @FXML
    void cancelar(ActionEvent e) {
        limpiarFormulario();
        productoEnEdicion = null;
    }

    private void limpiarFormulario() {
        cbxTipo.setValue(null);
        txtNombre.clear();
        txtPrecio.clear();
        txtStock.clear();
        cbxCategoria.setValue(null);
        cbxTalla.setValue(null);
        cbxMarca.setValue(null);
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Mensaje");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}