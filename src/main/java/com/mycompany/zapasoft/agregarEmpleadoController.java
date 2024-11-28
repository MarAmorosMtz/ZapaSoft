package com.mycompany.zapasoft;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class agregarEmpleadoController {
    
    @FXML
    private TextField txtid, txtnombre, txtcontraseña, txthorarioe, txthorarios, txttelefono, txtapellido, txtcorreo, txtpuesto;
    
    @FXML
    private void guardar(ActionEvent event) {

    Integer id = Integer.valueOf(txtid.getText());
    String nombre = txtnombre.getText();
    Integer contraseña = Integer.valueOf(txtcontraseña.getText());
    Time horarioE = Time.valueOf(txthorarioe.getText());
    Time horarioS = Time.valueOf(txthorarios.getText());
    String telefono = txttelefono.getText();
    String apellido = txtapellido.getText();
    String correo = txtcorreo.getText();
    String puesto = txtpuesto.getText();



    Connection conn = Conexion.connect();

    if (conn != null) {
        System.out.println("Conexión exitosa a la base de datos.");
        try {

            String sql = "INSERT INTO empleados (IdEMPLEADOS, Nombres, Contraseña, HorarioEntrada, Teléfono, HorarioSalida, Apellidos, Correo, Puesto) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";


            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, id);
            stmt.setString(2, nombre);
            stmt.setInt(3, contraseña);
            stmt.setTime(4, horarioE);
            stmt.setString(5, telefono);
            stmt.setTime(6, horarioS);
            stmt.setString(7, apellido);
            stmt.setString(8, correo);
            stmt.setString(9, puesto);


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
        Stage stage = (Stage)txtnombre.getScene().getWindow();
        stage.close();
    }
    
}
