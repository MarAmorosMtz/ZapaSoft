package com.mycompany.zapasoft;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class editarProveedorController {
    
    @FXML
    private TextField txtID, txtNombre, txtCorreo, txtDireccion, txtTelefono;
    
    public void initialize(){}

    @FXML
    private void guardar(){

    Connection conn = Conexion.connect();


    if (conn != null) {
        System.out.println("Conexión exitosa a la base de datos.");
        try {
            
            Integer id = Integer.valueOf(txtID.getText());
            String nombre = txtNombre.getText();
            String correo = txtCorreo.getText();
            String direccion = txtDireccion.getText();
            String telefono = txtTelefono.getText();

            String sql = "UPDATE proveedores SET NOMBRE = ?, CORREO = ?, DIRECCION = ?, TELEFONO = ? WHERE IdPROVEEDORES = ?";


            PreparedStatement stmt = conn.prepareStatement(sql);

            
            stmt.setString(1, nombre);
            stmt.setString(2, correo);
            stmt.setString(3, direccion);
            stmt.setString(4, telefono);
            stmt.setInt(5, id);


            int rowsAffected = stmt.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("Datos actualizados exitosamente.");
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

    public void setModel(Proveedor proveedor){
        this.txtNombre.setText(proveedor.getNombre());
        this.txtCorreo.setText(proveedor.getCorreo());
        this.txtDireccion.setText(proveedor.getDireccion());
        this.txtTelefono.setText(proveedor.getTelefono());
        this.txtID.setText(String.valueOf(proveedor.getId()));
    }
}
