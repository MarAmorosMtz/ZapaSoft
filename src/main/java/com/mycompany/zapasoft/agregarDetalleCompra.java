package com.mycompany.zapasoft;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class agregarDetalleCompra{


    @FXML
private TextField numc, total, precio, metodo, cantidad, idproducto, folioc, costo;

@FXML
private void guardar(ActionEvent event) {
    // Obtener valores de los campos de texto
    try {
        int numC = Integer.parseInt(numc.getText());
        float totalCompra = Float.parseFloat(total.getText());
        float precioUnitario = Float.parseFloat(precio.getText());
        String metodoDePago = metodo.getText();
        int cantidadProductos = Integer.parseInt(cantidad.getText());
        int idProducto = Integer.parseInt(idproducto.getText());
        int folioC = Integer.parseInt(folioc.getText());
        float costoProducto = Float.parseFloat(costo.getText()); // Añadir la conversión para 'costo'

        // Conexión a la base de datos
        Connection conn = Conexion.connect();

        if (conn != null) {
            System.out.println("Conexión exitosa a la base de datos.");
            String sql = "INSERT INTO detalledecompras (NUMC, TOTALDECOMPRA, PRECIO, METODODEPAGO, CANTIDAD, IdPRODUCTOS, FOLIOC, COSTO) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement stmt = conn.prepareStatement(sql);

            // Asignar parámetros, incluyendo el campo 'costo'
            stmt.setInt(1, numC);
            stmt.setFloat(2, totalCompra);
            stmt.setFloat(3, precioUnitario);
            stmt.setString(4, metodoDePago);
            stmt.setInt(5, cantidadProductos);
            stmt.setInt(6, idProducto);
            stmt.setInt(7, folioC);
            stmt.setFloat(8, costoProducto); // Asignamos el valor de costo

            // Ejecutar la consulta
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Detalle de compra guardado exitosamente.");
            } else {
                System.out.println("No se pudo guardar el detalle de compra.");
            }
        } else {
            System.out.println("No se pudo conectar a la base de datos.");
        }
    } catch (NumberFormatException e) {
        System.out.println("Error: Verifica los valores ingresados en los campos.");
        e.printStackTrace();
    } catch (SQLException e) {
        System.out.println("Error de base de datos.");
        e.printStackTrace();
    }

    cancelar();
}


    @FXML
    private void cancelar() {
        Stage stage = (Stage) numc.getScene().getWindow();
        stage.close();
    }
}
