package com.mycompany.zapasoft;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.GaussianBlur;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class productosController {
    
    @FXML
    private TableView<Producto> tablaProductos;
    @FXML
    private TableColumn<Producto, String> columnaID;
    @FXML
    private TableColumn<Producto, String> columnaStock;
    @FXML
    private TableColumn<Producto, String> columnaPrecio;
    @FXML
    private TableColumn<Producto, String> columnaCosto;
    @FXML
    private TableColumn<Producto, String> columnaNombre;
    @FXML
    private TableColumn<Producto, String> columnaTalla;
    
    @FXML
public void initialize() {

    Connection conn = Conexion.connect();
    
    if (conn != null) {
        try {
            String sql = "SELECT * FROM productos";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            ObservableList<Producto> productos = FXCollections.observableArrayList();

            while (rs.next()) {
                Producto producto = new Producto();
                producto.setId(rs.getInt("IdPRODUCTOS"));
                producto.setStock(rs.getInt("STOCK"));
                producto.setPrecio(rs.getFloat("PRECIO"));
                producto.setCosto(rs.getFloat("COSTO"));
                producto.setNombre(rs.getString("NOMBREPRODUCTO"));
                producto.setTalla(rs.getFloat("TALLA"));

                productos.add(producto);
            }

            // Configuración de las columnas
            columnaID.setCellValueFactory(new PropertyValueFactory<>("id"));
            columnaStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
            columnaPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
            columnaCosto.setCellValueFactory(new PropertyValueFactory<>("costo"));
            columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            columnaTalla.setCellValueFactory(new PropertyValueFactory<>("talla"));

            // Asignar datos a la tabla
            tablaProductos.setItems(productos);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    } else {
        System.out.println("No se pudo conectar a la base de datos.");
    }
}

@FXML
    private void agregar() throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("agregarProducto.fxml"));
    Parent parent = loader.load();

    Scene scene = new Scene(parent);
    Stage stage = new Stage();
    stage.initModality(Modality.APPLICATION_MODAL);
    stage.initOwner(stage.getOwner());
    stage.setResizable(false);
    stage.setMaximized(false);
    stage.setScene(scene);
    stage.show();
}
    @FXML
    private void editar() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("editarProducto.fxml"));
        Parent parent = loader.load();

        int rowIndex = tablaProductos.getSelectionModel().getSelectedIndex();

        if(rowIndex != -1){
        Producto producto = tablaProductos.getItems().get(rowIndex);

        editarProductoController editController = loader.getController();
        editController.setModel(producto);

        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(stage.getOwner());
        stage.setMaximized(false);
        stage.setResizable(false);

        stage.show();
        }
    }

    @FXML
    private void borrar() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("confirmacionProducto.fxml"));
        Parent parent = loader.load();

        int rowIndex = tablaProductos.getSelectionModel().getSelectedIndex();
        if(rowIndex != -1){
        Producto producto = tablaProductos.getItems().get(rowIndex);

        confirmacionProductoController confirmacionController = loader.getController();
        confirmacionController.setProducto(producto);

            GaussianBlur blurEffect = new GaussianBlur();
            blurEffect.setRadius(10);
            Stage mainStage = (Stage) tablaProductos.getScene().getWindow();
            mainStage.getScene().getRoot().setEffect(blurEffect);

        Stage stage = new Stage(StageStyle.UNDECORATED);
        stage.setScene(new Scene(parent));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(stage.getOwner());
        stage.setMaximized(false);
        stage.setResizable(false);

            stage.setOnHiding(event -> mainStage.getScene().getRoot().setEffect(null));

        stage.show();
        }
    }
    
    @FXML
private void actualizarTabla() {
    Connection conn = Conexion.connect();

    if (conn != null) {
        try {
            String sql = "SELECT * FROM productos";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            ObservableList<Producto> productos = FXCollections.observableArrayList();

            while (rs.next()) {
                Producto producto = new Producto();
                producto.setId(rs.getInt("IdPRODUCTOS"));
                producto.setStock(rs.getInt("STOCK"));
                producto.setPrecio(rs.getFloat("PRECIO"));
                producto.setCosto(rs.getFloat("COSTO"));
                producto.setNombre(rs.getString("NOMBREPRODUCTO"));
                producto.setTalla(rs.getFloat("TALLA"));

                productos.add(producto);
            }

            // Asignar los datos a la tabla
            columnaID.setCellValueFactory(new PropertyValueFactory<>("id"));
            columnaStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
            columnaPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
            columnaCosto.setCellValueFactory(new PropertyValueFactory<>("costo"));
            columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            columnaTalla.setCellValueFactory(new PropertyValueFactory<>("talla"));

            // Asignar la lista de productos a la tabla
            tablaProductos.setItems(productos);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    } else {
        System.out.println("No se pudo conectar a la base de datos.");
    }
}


}
