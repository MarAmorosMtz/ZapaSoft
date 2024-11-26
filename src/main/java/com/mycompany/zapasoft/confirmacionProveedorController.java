package com.mycompany.zapasoft;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class confirmacionProveedorController {
    
    @FXML
    private Button btnCancelar;

    private Proveedor proveedor;

    @FXML
    private void confirmar() {
    if (proveedor != null) {
        Connection conn = Conexion.connect();
        if (conn != null) {
            try {
                String sql = "DELETE FROM proveedores WHERE IdPROVEEDORES = ?";

                PreparedStatement stmt = conn.prepareStatement(sql);

                stmt.setInt(1, proveedor.getId());

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

    public void setProveedor(Proveedor proveedor){
        this.proveedor = proveedor;
    }
    
}
