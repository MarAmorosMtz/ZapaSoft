package com.mycompany.zapasoft;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class agregarProveedorController {
    
    @FXML
    private TextField txtID, txtNombre, txtCorreo, txtDireccion, txtTelefono;
    
    @FXML
    private void guardar(ActionEvent event) {

    Integer id = Integer.valueOf(txtID.getText());
    String nombre = txtNombre.getText();
    String correo = txtCorreo.getText();
    String direccion = txtDireccion.getText();
    String telefono = txtTelefono.getText();


    Connection conn = Conexion.connect();

    if (conn != null) {
        System.out.println("Conexión exitosa a la base de datos.");
        try {

            String sql = "INSERT INTO proveedores (IdPROVEEDORES, NOMBRE, CORREO, DIRECCION, TELEFONO) VALUES (?, ?, ?, ?, ?)";


            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, id);
            stmt.setString(2, nombre);
            stmt.setString(3, correo);
            stmt.setString(4, direccion);
            stmt.setString(5, telefono);


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
        cancelar();
}


    @FXML
    private void cancelar(){
        Stage stage = (Stage)txtNombre.getScene().getWindow();
        stage.close();
    }
    
        
    
}
