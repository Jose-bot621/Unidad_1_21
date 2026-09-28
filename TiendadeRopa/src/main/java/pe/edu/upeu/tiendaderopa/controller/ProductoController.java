package pe.edu.upeu.tiendaderopa.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import pe.edu.upeu.tiendaderopa.enums.Categoria;
import pe.edu.upeu.tiendaderopa.enums.Talla;
import pe.edu.upeu.tiendaderopa.exception.ProductoException;
import pe.edu.upeu.tiendaderopa.interfaces.ProductoService;
import pe.edu.upeu.tiendaderopa.model.Marca;
import pe.edu.upeu.tiendaderopa.model.Prenda;
import pe.edu.upeu.tiendaderopa.model.Producto;
import pe.edu.upeu.tiendaderopa.service.ProductoServiceImpl;

public class ProductoController {

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtPrecio;

    @FXML
    private TextField txtStock;

    @FXML
    private TextField txtBuscar;

    @FXML
    private ComboBox<Marca> cbxMarca;

    @FXML
    private ComboBox<Prenda> cbxPrenda;

    @FXML
    private ComboBox<Categoria> cbxCategoria;

    @FXML
    private ComboBox<Talla> cbxTalla;

    @FXML
    private TableView<Producto> tablaProductos;

    @FXML
    private TableColumn<Producto, Integer> colId;

    @FXML
    private TableColumn<Producto, String> colNombre;

    @FXML
    private TableColumn<Producto, Double> colPrecio;

    @FXML
    private TableColumn<Producto, Integer> colStock;

    @FXML
    private TableColumn<Producto, Marca> colMarca;

    @FXML
    private TableColumn<Producto, Prenda> colPrenda;

    @FXML
    private TableColumn<Producto, Categoria> colCategoria;

    @FXML
    private TableColumn<Producto, Talla> colTalla;

    private final ProductoService service =
            new ProductoServiceImpl();

    private final ObservableList<Producto> lista =
            FXCollections.observableArrayList();

    private Producto productoSeleccionado;

    @FXML
    public void initialize() {

        configurarTabla();

        cargarCombos();

        cargarProductos();

        tablaProductos.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, anterior, nuevo) -> {

                    if (nuevo != null) {

                        productoSeleccionado = nuevo;

                        cargarFormulario(nuevo);
                    }
                });
    }

    private void configurarTabla() {

        colId.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );

        colNombre.setCellValueFactory(
                new PropertyValueFactory<>("nombre")
        );

        colPrecio.setCellValueFactory(
                new PropertyValueFactory<>("precio")
        );

        colStock.setCellValueFactory(
                new PropertyValueFactory<>("stock")
        );

        colMarca.setCellValueFactory(
                new PropertyValueFactory<>("marca")
        );

        colPrenda.setCellValueFactory(
                new PropertyValueFactory<>("prenda")
        );

        colCategoria.setCellValueFactory(
                new PropertyValueFactory<>("categoria")
        );

        colTalla.setCellValueFactory(
                new PropertyValueFactory<>("talla")
        );
    }

    private void cargarCombos() {

        cbxMarca.setItems(
                FXCollections.observableArrayList(
                        new Marca(1, "Nike"),
                        new Marca(2, "Adidas"),
                        new Marca(3, "Puma"),
                        new Marca(4, "Zara"),
                        new Marca(5, "Levis")
                )
        );

        cbxPrenda.setItems(
                FXCollections.observableArrayList(
                        new Prenda(1, "Polo"),
                        new Prenda(2, "Pantalón"),
                        new Prenda(3, "Casaca"),
                        new Prenda(4, "Vestido"),
                        new Prenda(5, "Zapatilla")
                )
        );

        cbxCategoria.setItems(
                FXCollections.observableArrayList(
                        Categoria.values()
                )
        );

        cbxTalla.setItems(
                FXCollections.observableArrayList(
                        Talla.values()
                )
        );
    }

    private void cargarProductos() {

        lista.setAll(
                service.listar()
        );

        tablaProductos.setItems(lista);
    }

    private Producto obtenerFormulario() {

        String nombre =
                txtNombre.getText().trim();

        double precio =
                Double.parseDouble(
                        txtPrecio.getText().trim()
                );

        int stock =
                Integer.parseInt(
                        txtStock.getText().trim()
                );

        return new Producto(
                0,
                nombre,
                precio,
                stock,
                cbxMarca.getValue(),
                cbxPrenda.getValue(),
                cbxCategoria.getValue(),
                cbxTalla.getValue()
        );
    }

    private void cargarFormulario(Producto producto) {

        txtNombre.setText(
                producto.getNombre()
        );

        txtPrecio.setText(
                String.valueOf(
                        producto.getPrecio()
                )
        );

        txtStock.setText(
                String.valueOf(
                        producto.getStock()
                )
        );

        cbxMarca.setValue(
                producto.getMarca()
        );

        cbxPrenda.setValue(
                producto.getPrenda()
        );

        cbxCategoria.setValue(
                producto.getCategoria()
        );

        cbxTalla.setValue(
                producto.getTalla()
        );
    }

    @FXML
    private void guardarProducto(ActionEvent event) {

        try {

            Producto producto =
                    obtenerFormulario();

            if (productoSeleccionado == null) {

                service.guardar(producto);

                mensaje(
                        Alert.AlertType.INFORMATION,
                        "Producto registrado correctamente."
                );

            } else {

                producto.setId(
                        productoSeleccionado.getId()
                );

                service.actualizar(producto);

                mensaje(
                        Alert.AlertType.INFORMATION,
                        "Producto actualizado correctamente."
                );
            }

            limpiar();

            cargarProductos();

        } catch (ProductoException e) {

            mensaje(
                    Alert.AlertType.ERROR,
                    e.getMessage()
            );

        } catch (NumberFormatException e) {

            mensaje(
                    Alert.AlertType.ERROR,
                    "Precio y stock deben ser números válidos."
            );
        }
    }

    @FXML
    private void editarProducto(ActionEvent event) {

        Producto seleccionado =
                tablaProductos.getSelectionModel()
                        .getSelectedItem();

        if (seleccionado == null) {

            mensaje(
                    Alert.AlertType.WARNING,
                    "Seleccione un producto."
            );

            return;
        }

        productoSeleccionado = seleccionado;

        cargarFormulario(seleccionado);
    }

    @FXML
    private void eliminarProducto(ActionEvent event) {

        Producto seleccionado =
                tablaProductos.getSelectionModel()
                        .getSelectedItem();

        if (seleccionado == null) {

            mensaje(
                    Alert.AlertType.WARNING,
                    "Seleccione un producto."
            );

            return;
        }

        Alert confirmacion =
                new Alert(Alert.AlertType.CONFIRMATION);

        confirmacion.setTitle("Eliminar producto");

        confirmacion.setHeaderText(null);

        confirmacion.setContentText(
                "¿Está seguro de eliminar este producto?"
        );

        if (confirmacion.showAndWait()
                .orElse(ButtonType.CANCEL)
                == ButtonType.OK) {

            try {

                service.eliminar(
                        seleccionado.getId()
                );

                cargarProductos();

                limpiar();

                mensaje(
                        Alert.AlertType.INFORMATION,
                        "Producto eliminado correctamente."
                );

            } catch (ProductoException e) {

                mensaje(
                        Alert.AlertType.ERROR,
                        e.getMessage()
                );
            }
        }
    }

    @FXML
    private void buscarProducto(ActionEvent event) {

        String texto =
                txtBuscar.getText()
                        .trim()
                        .toLowerCase();

        if (texto.isEmpty()) {

            cargarProductos();

            return;
        }

        ObservableList<Producto> resultado =
                FXCollections.observableArrayList();

        for (Producto producto : lista) {

            if (producto.getNombre()
                    .toLowerCase()
                    .contains(texto)) {

                resultado.add(producto);
            }
        }

        tablaProductos.setItems(resultado);
    }

    @FXML
    private void limpiarFormulario(ActionEvent event) {

        limpiar();
    }

    private void limpiar() {

        txtNombre.clear();
        txtPrecio.clear();
        txtStock.clear();
        txtBuscar.clear();

        cbxMarca.setValue(null);
        cbxPrenda.setValue(null);
        cbxCategoria.setValue(null);
        cbxTalla.setValue(null);

        productoSeleccionado = null;

        tablaProductos.getSelectionModel()
                .clearSelection();

        cargarProductos();
    }

    private void mensaje(
            Alert.AlertType tipo,
            String texto) {

        Alert alerta = new Alert(tipo);

        alerta.setTitle("Tienda de Ropa");

        alerta.setHeaderText(null);

        alerta.setContentText(texto);

        alerta.showAndWait();
    }
}