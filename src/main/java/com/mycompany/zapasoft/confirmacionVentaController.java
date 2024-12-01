package com.mycompany.zapasoft;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class confirmacionVentaController {
    @FXML
    private Button btnCancelar;

    private Venta venta;

    @FXML
    private void confirmar() {
    if (venta != null) {
        Connection conn = Conexion.connect();
        if (conn != null) {
            try {
                String sql = "DELETE FROM venta WHERE FOLIOV = ?";

                PreparedStatement stmt = conn.prepareStatement(sql);

                stmt.setInt(1, venta.getFolio());

                int rowsDeleted = stmt.executeUpdate();

                if (rowsDeleted > 0) {
                    System.out.println("Proveedor eliminado con éxito.");
                } else {
                    System.out.println("No se encontró un proveedor con ese ID.");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No se pudo conectar a la base de datos.");
        }
    } else {
        System.out.println("No hay un proveedor seleccionado para eliminar.");
    }
    cancelar();
}


    @FXML
    private void cancelar(){
        Stage stage = (Stage)btnCancelar.getScene().getWindow();
        stage.close();
    }

    public void setVenta(Venta venta){
        this.venta = venta;
    }
}
