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

public class compraController {
    
    @FXML
    private TableView<Compra> tablaCompras;
    @FXML
    private TableColumn<Compra, String> columnaFolio;
    @FXML
    private TableColumn<Compra, String> columnaEmpleado;
    @FXML
    private TableColumn<Compra, String> columnaProveedor;
    @FXML
    private TableColumn<Compra, String> columnaFecha;
    
    @FXML
public void initialize() {

    Connection conn = Conexion.connect();
    
    if (conn != null) {
        try {
            String sql = "SELECT * FROM compra";  // Cambié a la tabla 'compras'
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            ObservableList<Compra> compras = FXCollections.observableArrayList();

            while (rs.next()) {
                Compra compra = new Compra(
                    rs.getInt("FOLIOC"),
                    rs.getInt("IdEMPLEADOS"),
                    rs.getInt("IdPROVEEDORES"),
                    rs.getDate("FECHA")
                );

                compras.add(compra);
            }

            // Asignar los datos a las columnas de la tabla
            columnaFolio.setCellValueFactory(new PropertyValueFactory<>("folio"));
            columnaEmpleado.setCellValueFactory(new PropertyValueFactory<>("empleado"));
            columnaProveedor.setCellValueFactory(new PropertyValueFactory<>("proveedor"));
            columnaFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));

            tablaCompras.setItems(compras);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    } else {
        System.out.println("No se pudo conectar a la base de datos.");
    }
}

@FXML
    private void agregar() throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("agregarCompra.fxml"));
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("editarCompra.fxml"));
        Parent parent = loader.load();

        int rowIndex = tablaCompras.getSelectionModel().getSelectedIndex();

        if(rowIndex != -1){
        Compra compra = tablaCompras.getItems().get(rowIndex);

        editarCompraController editController = loader.getController();
        editController.setModel(compra);

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("confirmacionCompra.fxml"));
        Parent parent = loader.load();

        int rowIndex = tablaCompras.getSelectionModel().getSelectedIndex();
        if(rowIndex != -1){
        Compra compra = tablaCompras.getItems().get(rowIndex);

        confirmacionCompraController confirmacionController = loader.getController();
        confirmacionController.setCompra(compra);

            GaussianBlur blurEffect = new GaussianBlur();
            blurEffect.setRadius(10);
            Stage mainStage = (Stage) tablaCompras.getScene().getWindow();
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
            String sql = "SELECT * FROM compra"; // Consulta a la tabla 'compras'
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            ObservableList<Compra> compras = FXCollections.observableArrayList();

            while (rs.next()) {
                Compra compra = new Compra(
                    rs.getInt("FOLIOC"),
                    rs.getInt("IdEMPLEADOS"),
                    rs.getInt("IdPROVEEDORES"),
                    rs.getDate("FECHA")
                );

                compras.add(compra);
            }

            // Asignar los datos a las columnas de la tabla
            columnaFolio.setCellValueFactory(new PropertyValueFactory<>("folio"));
            columnaEmpleado.setCellValueFactory(new PropertyValueFactory<>("empleado"));
            columnaProveedor.setCellValueFactory(new PropertyValueFactory<>("proveedor"));
            columnaFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));

            tablaCompras.setItems(compras);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    } else {
        System.out.println("No se pudo conectar a la base de datos.");
    }
}

}
