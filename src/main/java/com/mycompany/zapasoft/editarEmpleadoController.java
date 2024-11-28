package com.mycompany.zapasoft;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class editarEmpleadoController {
    
    @FXML
    private TextField txtid, txtnombre, txtcontraseña, txthorarioe, txthorarios, txttelefono, txtapellido, txtcorreo, txtpuesto;
    
    @FXML
    private void guardar(){

    Connection conn = Conexion.connect();


    if (conn != null) {
        System.out.println("Conexión exitosa a la base de datos.");
        try {
            
            Integer id = Integer.valueOf(txtid.getText());
            String nombre = txtnombre.getText();
            Integer contraseña = Integer.valueOf(txtcontraseña.getText());
            Time horarioE = Time.valueOf(txthorarioe.getText());
            Time horarioS = Time.valueOf(txthorarios.getText());
            String telefono = txttelefono.getText();
            String apellido = txtapellido.getText();
            String correo = txtcorreo.getText();
            String puesto = txtpuesto.getText();

            String sql = "UPDATE empleados SET Nombres = ?, Contraseña = ?, HorarioEntrada = ?, Teléfono = ?, HorarioSalida = ?, Apellidos = ?, Correo = ?, Puesto = ? WHERE IdEMPLEADOS = ?";

PreparedStatement stmt = conn.prepareStatement(sql);


stmt.setString(1, nombre);
stmt.setInt(2, contraseña);
stmt.setTime(3, horarioE);
stmt.setString(4, telefono);
stmt.setTime(5, horarioS);
stmt.setString(6, apellido);
stmt.setString(7, correo);
stmt.setString(8, puesto);
stmt.setInt(9, id);



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
        Stage stage = (Stage)txtnombre.getScene().getWindow();
        stage.close();
    }

    public void setModel(Empleado empleado) {
    this.txtid.setText(String.valueOf(empleado.getId()));
    this.txtnombre.setText(empleado.getNombre());
    this.txtcontraseña.setText(String.valueOf(empleado.getContraseña()));
    this.txthorarioe.setText(empleado.getHorarioe() != null ? empleado.getHorarioe().toString() : "");
    this.txthorarios.setText(empleado.getHorarios() != null ? empleado.getHorarios().toString() : "");
    this.txttelefono.setText(empleado.getTelefono());
    this.txtapellido.setText(empleado.getApellido());
    this.txtcorreo.setText(empleado.getCorreo());
    this.txtpuesto.setText(empleado.getPuesto());
}

}
