package com.mycompany.zapasoft;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class confirmacionCompraController {
    
    private Compra compra;
    
    @FXML
    private Button btnCancelar;    
    
    @FXML
    private void confirmar() {
    if (compra != null) {
        Connection conn = Conexion.connect();
        if (conn != null) {
            try {
                String sql = "DELETE FROM compra WHERE FOLIOC = ?";

                PreparedStatement stmt = conn.prepareStatement(sql);

                stmt.setInt(1, compra.getFolio());

                int rowsDeleted = stmt.executeUpdate();

                if (rowsDeleted > 0) {
                    System.out.println("Compra eliminado con éxito.");
                } else {
                    System.out.println("No se encontró una compra con ese ID.");
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

    public void setCompra(Compra compra){
        this.compra = compra;
    }
    
}
