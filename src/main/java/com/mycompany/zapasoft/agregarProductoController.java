package com.mycompany.zapasoft;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class agregarProductoController {
    
    @FXML
    private TextField txtid, txtnombre, txtcosto, txtprecio, txttalla, txtstock;
    
    @FXML
private void guardar(ActionEvent event) {

    // Obtener los datos del formulario
    Integer id = Integer.valueOf(txtid.getText());
    String nombre = txtnombre.getText();
    float costo = Float.valueOf(txtcosto.getText());
    float precio = Float.valueOf(txtprecio.getText());
    float talla = Float.valueOf(txttalla.getText());
    Integer stock = Integer.valueOf(txtstock.getText());

    // Establecer la conexión con la base de datos
    Connection conn = Conexion.connect();

    if (conn != null) {
        System.out.println("Conexión exitosa a la base de datos.");
        try {
            // Consulta SQL para insertar los datos
            String sql = "INSERT INTO productos (IdPRODUCTOS, NOMBREPRODUCTO, COSTO, PRECIO, TALLA, STOCK) VALUES (?, ?, ?, ?, ?, ?)";

            // Preparar la consulta
            PreparedStatement stmt = conn.prepareStatement(sql);

            // Establecer los valores para cada parámetro
            stmt.setInt(1, id);
            stmt.setString(2, nombre);
            stmt.setFloat(3, costo);
            stmt.setFloat(4, precio);
            stmt.setFloat(5, talla);
            stmt.setInt(6, stock);

            // Ejecutar la actualización (inserción)
            int rowsAffected = stmt.executeUpdate();

            // Comprobar si los datos se guardaron correctamente
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

    // Llamar al método para cancelar o limpiar los campos
    cancelar();
}
    @FXML
    private void cancelar(){
        Stage stage = (Stage)txtnombre.getScene().getWindow();
        stage.close();
    }
}
