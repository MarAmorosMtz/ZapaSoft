package com.mycompany.zapasoft;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

public class editDVenta {

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

    private String originalNumVenta;  // Guardamos el número de venta original para poder buscar y actualizar el registro correspondiente

    // Método para cargar los datos del detalle de venta que se desea editar
    public void cargarDetalle(String numVenta) {
        // Establecer conexión con la base de datos
        Connection conn = Conexion.connect();
        if (conn != null) {
            try {
                // Consulta SQL para obtener el detalle de venta
                String sql = "SELECT * FROM detalledeventas WHERE NUMV = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, numVenta);

                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    // Cargar los datos en los campos
                    originalNumVenta = rs.getString("NUMV");
                    numv.setText(rs.getString("NUMV"));
                    idproducto.setText(rs.getString("IdPRODUCTOS"));
                    total.setText(rs.getString("TOTALDEVENTA"));
                    precio.setText(rs.getString("PRECIO"));
                    costo.setText(rs.getString("COSTO"));
                    metodo.setText(rs.getString("METODODEPAGO"));
                    cantidad.setText(rs.getString("CANTIDAD"));
                    foliov.setText(rs.getString("FOLIOV"));
                } else {
                    // Si no se encuentra el detalle, mostrar un mensaje de error
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Detalle de venta no encontrado");
                    alert.setContentText("No se pudo encontrar el detalle de venta con el número de venta proporcionado.");
                    alert.showAndWait();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Mostrar un mensaje de error si ocurre un error de SQL
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error de base de datos");
                alert.setHeaderText("Error al cargar los datos");
                alert.setContentText("Hubo un error al intentar cargar los datos del detalle de venta.");
                alert.showAndWait();
            }
        }
    }

    // Método para actualizar los datos en la base de datos
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
                // Consulta SQL para actualizar el detalle de la venta
                String sql = "UPDATE detalledeventas SET IdPRODUCTOS = ?, TOTALDEVENTA = ?, PRECIO = ?, COSTO = ?, METODODEPAGO = ?, CANTIDAD = ?, FOLIOV = ? WHERE NUMV = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);

                // Configurar los parámetros de la consulta
                stmt.setString(1, idProducto);
                stmt.setString(2, totalVenta);
                stmt.setString(3, precioProducto);
                stmt.setString(4, costoProducto);
                stmt.setString(5, metodoPago);
                stmt.setString(6, cantidadProducto);
                stmt.setString(7, folioVenta);
                stmt.setString(8, numVenta);

                // Ejecutar la actualización
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    // Si se actualizó correctamente
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Éxito");
                    alert.setHeaderText("Detalle de Venta Actualizado");
                    alert.setContentText("El detalle de venta ha sido actualizado exitosamente.");
                    alert.showAndWait();

                    // Limpiar los campos después de la actualización
                    cancelar();
                } else {
                    // Si hubo un error al actualizar
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Error al actualizar el detalle de venta");
                    alert.setContentText("Hubo un problema al intentar actualizar el detalle de venta.");
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
    
    public void setModel(detalleVenta venta) {
        this.numv.setText(String.valueOf(venta.getNumv()));
        this.idproducto.setText(String.valueOf(venta.getIdProductos()));
        this.total.setText(String.valueOf(venta.getTotalDeVenta()));
        this.precio.setText(String.valueOf(venta.getPrecio()));
        this.costo.setText(String.valueOf(venta.getCosto()));
        this.metodo.setText(String.valueOf(venta.getMetodoDePago()));
        this.cantidad.setText(String.valueOf(venta.getCantidad()));
        this.foliov.setText(String.valueOf(venta.getFolioV()));
    }
}
