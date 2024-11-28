package com.mycompany.zapasoft;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class editarProductoController {
    
    @FXML
    private TextField txtid, txtnombre, txtcosto, txtprecio, txttalla, txtstock;    
    
    @FXML
private void guardar() {

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
            // Consulta SQL para actualizar los datos en la tabla productos
            String sql = "UPDATE productos SET NOMBREPRODUCTO = ?, COSTO = ?, PRECIO = ?, TALLA = ?, STOCK = ? WHERE IdPRODUCTOS = ?";

            // Preparar la consulta
            PreparedStatement stmt = conn.prepareStatement(sql);

            // Establecer los valores para cada parámetro
            stmt.setString(1, nombre);
            stmt.setFloat(2, costo);
            stmt.setFloat(3, precio);
            stmt.setFloat(4, talla);
            stmt.setInt(5, stock);
            stmt.setInt(6, id);

            // Ejecutar la actualización
            int rowsAffected = stmt.executeUpdate();

            // Comprobar si los datos se actualizaron correctamente
            if (rowsAffected > 0) {
                System.out.println("Datos actualizados exitosamente.");
            } else {
                System.out.println("No se pudo actualizar los datos.");
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
    
    public void setModel(Producto producto) {
    this.txtnombre.setText(producto.getNombre());
    this.txtcosto.setText(String.valueOf(producto.getCosto()));
    this.txtprecio.setText(String.valueOf(producto.getPrecio()));
    this.txttalla.setText(String.valueOf(producto.getTalla()));
    this.txtstock.setText(String.valueOf(producto.getStock()));
    this.txtid.setText(String.valueOf(producto.getId()));
}

    
}
