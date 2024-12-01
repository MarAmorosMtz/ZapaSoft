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

public class ventaController {
    
    @FXML
    private TableView<Venta> tablaVentas;
    @FXML
    private TableColumn<Venta, String> columnaFolio;
    @FXML
    private TableColumn<Venta, String> columnaEmpleado;
    @FXML
    private TableColumn<Venta, String> columnaNumV;
    @FXML
    private TableColumn<Venta, String> columnaFecha;
    @FXML
    
public void initialize() {
    Connection conn = Conexion.connect();

    if (conn != null) {
        try {
            String sql = "SELECT * FROM venta";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            ObservableList<Venta> ventas = FXCollections.observableArrayList();            

            while (rs.next()) {
                Venta venta = new Venta(
                    rs.getInt("FOLIOV"), 
                    rs.getInt("IdEMPLEADOS"), 
                    rs.getInt("NUMV"), 
                    rs.getDate("FECHA")
                );

                ventas.add(venta);
            }

            // Establecer las propiedades de las columnas
            columnaFolio.setCellValueFactory(new PropertyValueFactory<>("folio"));
            columnaEmpleado.setCellValueFactory(new PropertyValueFactory<>("idempleado"));
            columnaNumV.setCellValueFactory(new PropertyValueFactory<>("numv"));
            columnaFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));

            // Establecer los datos en la tabla
            tablaVentas.setItems(ventas);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    } else {
        System.out.println("No se pudo conectar a la base de datos.");
    }
}

@FXML
    private void agregar() throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("agregarVenta.fxml"));
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("editarVenta.fxml"));
        Parent parent = loader.load();

        int rowIndex = tablaVentas.getSelectionModel().getSelectedIndex();

        if(rowIndex != -1){
        Venta venta = tablaVentas.getItems().get(rowIndex);

        editarVentaController editController = loader.getController();
        editController.setModel(venta);

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("confirmacionVenta.fxml"));
        Parent parent = loader.load();

        int rowIndex = tablaVentas.getSelectionModel().getSelectedIndex();
        if(rowIndex != -1){
        Venta venta = tablaVentas.getItems().get(rowIndex);

        confirmacionVentaController confirmacionController = loader.getController();
        confirmacionController.setVenta(venta);

            GaussianBlur blurEffect = new GaussianBlur();
            blurEffect.setRadius(10);
            Stage mainStage = (Stage) tablaVentas.getScene().getWindow();
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
            String sql = "SELECT * FROM venta";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            ObservableList<Venta> ventas = FXCollections.observableArrayList();

            while (rs.next()) {
                Venta venta = new Venta(
                    rs.getInt("FOLIOV"), 
                    rs.getInt("IdEMPLEADOS"), 
                    rs.getInt("NUMV"), 
                    rs.getDate("FECHA")
                );
                ventas.add(venta);
            }

            // Asignar los datos a la tabla
            columnaFolio.setCellValueFactory(new PropertyValueFactory<>("folio"));
            columnaEmpleado.setCellValueFactory(new PropertyValueFactory<>("idempleado"));
            columnaNumV.setCellValueFactory(new PropertyValueFactory<>("numv"));
            columnaFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));

            tablaVentas.setItems(ventas);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    } else {
        System.out.println("No se pudo conectar a la base de datos.");
    }
}


}
