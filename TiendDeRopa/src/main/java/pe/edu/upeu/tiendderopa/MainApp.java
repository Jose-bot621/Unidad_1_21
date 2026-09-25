package pe.edu.upeu.tiendderopa;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.<Parent>load(getClass().getResource("/main_producto.fxml"));
        Scene sc = new Scene(root, 950, 650);
        stage.setTitle("Gestionar Productos");
        stage.setScene(sc);
        stage.show();
    }
    public static void main(String[] args) { launch(); }
}