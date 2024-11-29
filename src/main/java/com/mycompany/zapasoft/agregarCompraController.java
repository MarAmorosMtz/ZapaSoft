package com.mycompany.zapasoft;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class agregarCompraController {
    
    @FXML
    private TextField txtfolio;
    @FXML
    private ComboBox comboEmpleado, comboProveedor;
    @FXML
    private DatePicker datefecha;
    
    @FXML
    public void initialize() {
        cargarProveedores();
        cargarEmpleados();
    }

    private void cargarProveedores() {
        Connection conn = Conexion.connect();
        if (conn != null) {
            try {
                String sql = "SELECT IdPROVEEDORES FROM proveedores";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                ObservableList<Integer> proveedores = FXCollections.observableArrayList();

                // Agregar todas las IDs de proveedores al ObservableList
                while (rs.next()) {
                    proveedores.add(rs.getInt("IdPROVEEDORES"));
                }

                // Asignar los datos al ComboBox
                comboProveedor.setItems(proveedores);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No se pudo conectar a la base de datos para cargar proveedores.");
        }
    }
    
    private void cargarEmpleados() {
        Connection conn = Conexion.connect();
        if (conn != null) {
            try {
                String sql = "SELECT IdEMPLEADOS FROM empleados";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                ObservableList<Integer> empleados = FXCollections.observableArrayList();

                // Agregar todas las IDs de empleados al ObservableList
                while (rs.next()) {
                    empleados.add(rs.getInt("IdEMPLEADOS"));
                }

                // Asignar los datos al ComboBox
                comboEmpleado.setItems(empleados);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No se pudo conectar a la base de datos para cargar empleados.");
        }
    }
    
    @FXML
private void guardar(ActionEvent event) {

    Integer folio = Integer.valueOf(txtfolio.getText()); // Folio de la compra
    Integer empleado = (Integer) comboEmpleado.getValue(); // Empleado seleccionado en el ComboBox
    Integer proveedor = (Integer) comboProveedor.getValue(); // Proveedor seleccionado en el ComboBox
    java.sql.Date fecha = java.sql.Date.valueOf(datefecha.getValue()); // Fecha seleccionada en el DatePicker

    Connection conn = Conexion.connect();

    if (conn != null) {
        System.out.println("Conexión exitosa a la base de datos.");
        try {

            String sql = "INSERT INTO compra (FOLIOC, IdEMPLEADOS, IdPROVEEDORES, FECHA) VALUES (?, ?, ?, ?)";

            PreparedStatement stmt = conn.prepareStatement(sql);

            // Configurar los valores para el PreparedStatement
            stmt.setInt(1, folio);
            stmt.setInt(2, empleado);
            stmt.setInt(3, proveedor);
            stmt.setDate(4, fecha);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Datos guardados exitosamente.");
            } else {
                System.out.println("No se pudo guardar los datos.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } else {
        System.out.println("No se pudo conectar a la base de datos.");
    }

    cancelar(); // Método para limpiar campos o realizar acciones posteriores (asegúrate de tenerlo implementado)
}
@FXML
    private void cancelar(){
        Stage stage = (Stage)txtfolio.getScene().getWindow();
        stage.close();
    }

}
