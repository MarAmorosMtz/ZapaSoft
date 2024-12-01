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

public class editarVentaController {
    @FXML
    private TextField txtfolio, txtnumv;
    @FXML
    private ComboBox comboEmpleado;
    @FXML
    private DatePicker datefecha;
    
    @FXML
    public void initialize() {
        cargarEmpleados();
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
    Integer numv = Integer.valueOf(txtnumv.getText()); // numv
    Integer empleado = (Integer) comboEmpleado.getValue(); // Empleado seleccionado en el ComboBox
    java.sql.Date fecha = java.sql.Date.valueOf(datefecha.getValue()); // Fecha seleccionada en el DatePicker

    Connection conn = Conexion.connect();

    if (conn != null) {
        System.out.println("Conexión exitosa a la base de datos.");
        try {

            // Cambiar la consulta SQL a una sentencia UPDATE
            String sql = "UPDATE venta SET IdEMPLEADOS = ?, NUMV = ?, FECHA = ? WHERE FOLIOV = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);

            // Configurar los valores para el PreparedStatement
            stmt.setInt(1, empleado); // Actualizar el ID del empleado
            stmt.setInt(2, numv); // Actualizar el ID del proveedor
            stmt.setDate(3, fecha); // Actualizar la fecha
            stmt.setInt(4, folio); // Usar el folio como referencia para la fila que se va a editar

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Datos actualizados exitosamente.");
            } else {
                System.out.println("No se pudo actualizar los datos. Verifica el folio.");
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
    
    public void setModel(Venta venta) {
    this.txtfolio.setText(String.valueOf(venta.getFolio()));
    this.comboEmpleado.setValue(venta.getIdempleado());
    this.txtnumv.setText(String.valueOf(venta.getNumv()));
    this.datefecha.setValue(venta.getFecha() != null ? venta.getFecha().toLocalDate() : null);
}
    
}