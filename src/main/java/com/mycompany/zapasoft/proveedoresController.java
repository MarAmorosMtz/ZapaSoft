package com.mycompany.zapasoft;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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

public class proveedoresController {
    
    @FXML
    public TableView <Proveedor> tablaProveedor;
    @FXML
    private TableColumn<Proveedor, Integer> columnaID;
    @FXML
    private TableColumn<Proveedor, String> columnaNombre;
    @FXML
    private TableColumn<Proveedor, String> columnaDireccion;
    @FXML
    private TableColumn<Proveedor, String> columnaCorreo;
    @FXML
    private TableColumn<Proveedor, String> columnaTelefono;
    
    @FXML
    public void initialize() {

        Connection conn = Conexion.connect();
        
        if (conn != null) {
             try {
            String sql = "SELECT * FROM proveedores";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            

            ObservableList<Proveedor> proveedores = FXCollections.observableArrayList();


            while (rs.next()) {
                Proveedor proveedor = new Proveedor();
                proveedor.setId(rs.getInt("IdPROVEEDORES"));
                proveedor.setNombre(rs.getString("NOMBRE"));
                proveedor.setDireccion(rs.getString("DIRECCION"));
                proveedor.setCorreo(rs.getString("CORREO"));
                proveedor.setTelefono(rs.getString("TELEFONO"));

                proveedores.add(proveedor);
            }

            columnaID.setCellValueFactory(new PropertyValueFactory<>("id"));
            columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            columnaDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
            columnaCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
            columnaTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));


            tablaProveedor.setItems(proveedores);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        } else {
            System.out.println("No se pudo conectar a la base de datos.");
        }
    }
    
    @FXML
    private void agregar() throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("agregarProveedor.fxml"));
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("editarProveedor.fxml"));
        Parent parent = loader.load();

        int rowIndex = tablaProveedor.getSelectionModel().getSelectedIndex();

        if(rowIndex != -1){
        Proveedor proveedor = tablaProveedor.getItems().get(rowIndex);

        editarProveedorController editController = loader.getController();
        editController.setModel(proveedor);

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("confirmacionProveedor.fxml"));
        Parent parent = loader.load();

        int rowIndex = tablaProveedor.getSelectionModel().getSelectedIndex();
        if(rowIndex != -1){
        Proveedor proveedor = tablaProveedor.getItems().get(rowIndex);

        confirmacionProveedorController confirmacionController = loader.getController();
        confirmacionController.setProveedor(proveedor);

            GaussianBlur blurEffect = new GaussianBlur();
            blurEffect.setRadius(10);
            Stage mainStage = (Stage) tablaProveedor.getScene().getWindow();
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
            String sql = "SELECT * FROM proveedores";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            ObservableList<Proveedor> proveedores = FXCollections.observableArrayList();

            while (rs.next()) {
                Proveedor proveedor = new Proveedor();
                proveedor.setId(rs.getInt("IdPROVEEDORES"));
                proveedor.setNombre(rs.getString("NOMBRE"));
                proveedor.setDireccion(rs.getString("DIRECCION"));
                proveedor.setCorreo(rs.getString("CORREO"));
                proveedor.setTelefono(rs.getString("TELEFONO"));
                proveedores.add(proveedor);
            }

            // Asignar los datos a la tabla
            columnaID.setCellValueFactory(new PropertyValueFactory<>("id"));
            columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            columnaDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
            columnaCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
            columnaTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));

            tablaProveedor.setItems(proveedores);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    } else {
        System.out.println("No se pudo conectar a la base de datos.");
    }
}
    
}
