package com.mycompany.zapasoft;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

public class addDVenta {

    @FXML
    private TextField numv;        // Número de venta
    @FXML
    private TextField idproducto;  // ID del producto
    @FXML
    private TextField total;       // Total de la venta
    @FXML
    private TextField precio;      // Precio del producto
    @FXML
    private TextField costo;       // Costo del producto
    @FXML
    private TextField metodo;      // Método de pago
    @FXML
    private TextField cantidad;    // Cantidad de productos
    @FXML
    private TextField foliov;      // Folio de la venta

    // Método para guardar los datos en la base de datos
    @FXML
    private void guardar(ActionEvent event) {
        // Obtener los datos de los campos de texto
        String numVenta = numv.getText();
        String idProducto = idproducto.getText();
        String totalVenta = total.getText();
        String precioProducto = precio.getText();
        String costoProducto = costo.getText();
        String metodoPago = metodo.getText();
        String cantidadProducto = cantidad.getText();
        String folioVenta = foliov.getText();

        // Validar que los campos no estén vacíos
        if (numVenta.isEmpty() || idProducto.isEmpty() || totalVenta.isEmpty() ||
            precioProducto.isEmpty() || costoProducto.isEmpty() || metodoPago.isEmpty() ||
            cantidadProducto.isEmpty() || folioVenta.isEmpty()) {

            // Mostrar un mensaje de error si algún campo está vacío
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Campos incompletos");
            alert.setContentText("Por favor, completa todos los campos.");
            alert.showAndWait();
            return;
        }

        // Establecer conexión con la base de datos
        Connection conn = Conexion.connect();
        if (conn != null) {
            try {
                // Consulta SQL para insertar el detalle de la venta
                String sql = "INSERT INTO detalledeventas (NUMV, IdPRODUCTOS, TOTALDEVENTA, PRECIO, COSTO, METODODEPAGO, CANTIDAD, FOLIOV) " +
                             "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(sql);

                // Configurar los parámetros de la consulta
                stmt.setString(1, numVenta);
                stmt.setString(2, idProducto);
                stmt.setString(3, totalVenta);
                stmt.setString(4, precioProducto);
                stmt.setString(5, costoProducto);
                stmt.setString(6, metodoPago);
                stmt.setString(7, cantidadProducto);
                stmt.setString(8, folioVenta);

                // Ejecutar la inserción
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    // Si se insertó correctamente
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Éxito");
                    alert.setHeaderText("Detalle de Venta Agregado");
                    alert.setContentText("El detalle de venta ha sido agregado exitosamente.");
                    alert.showAndWait();

                    // Limpiar los campos después de la inserción
                    cancelar();
                } else {
                    // Si hubo un error al insertar
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Error al agregar el detalle de venta");
                    alert.setContentText("Hubo un problema al intentar agregar el detalle de venta.");
                    alert.showAndWait();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Mostrar un mensaje de error si ocurre un error de SQL
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error de base de datos");
                alert.setHeaderText("No se pudo conectar a la base de datos");
                alert.setContentText("Hubo un error al conectarse a la base de datos. Intenta de nuevo.");
                alert.showAndWait();
            }
        }
    }

    // Método para cancelar y limpiar los campos
    @FXML
    private void cancelar() {
        // Limpiar todos los campos
        numv.clear();
        idproducto.clear();
        total.clear();
        precio.clear();
        costo.clear();
        metodo.clear();
        cantidad.clear();
        foliov.clear();
    }
}
