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

public class editarDetalleCompra{

    @FXML
    private TextField numc;        // Número de compra
    @FXML
    private TextField idproducto;  // ID del producto
    @FXML
    private TextField total;       // Total de la compra
    @FXML
    private TextField precio;      // Precio del producto
    @FXML
    private TextField metodo;      // Método de pago
    @FXML
    private TextField cantidad;    // Cantidad de productos
    @FXML
    private TextField folioc;      // Folio de la compra
    
@FXML
    private TextField costo;      // Folio de la compra
    private String originalNumCompra;  // Guardamos el número de compra original para poder buscar y actualizar el registro correspondiente

    // Método para cargar los datos del detalle de compra que se desea editar
public void cargarDetalle(String numCompra) {
    // Establecer conexión con la base de datos
    Connection conn = Conexion.connect();
    if (conn != null) {
        try {
            // Consulta SQL para obtener el detalle de compra
            String sql = "SELECT * FROM detalledecompras WHERE NUMC = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, numCompra);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Cargar los datos en los campos
                originalNumCompra = rs.getString("NUMC");
                numc.setText(rs.getString("NUMC"));
                idproducto.setText(rs.getString("IdPRODUCTOS"));
                total.setText(rs.getString("TOTALDECOMPRA"));
                precio.setText(rs.getString("PRECIO"));
                metodo.setText(rs.getString("METODODEPAGO"));
                cantidad.setText(rs.getString("CANTIDAD"));
                folioc.setText(rs.getString("FOLIOC"));
                costo.setText(rs.getString("COSTO"));  // Cargar el valor de COSTO
            } else {
                // Si no se encuentra el detalle, mostrar un mensaje de error
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Detalle de compra no encontrado");
                alert.setContentText("No se pudo encontrar el detalle de compra con el número de compra proporcionado.");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Mostrar un mensaje de error si ocurre un error de SQL
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error de base de datos");
            alert.setHeaderText("Error al cargar los datos");
            alert.setContentText("Hubo un error al intentar cargar los datos del detalle de compra.");
            alert.showAndWait();
        }
    }
}


    // Método para actualizar los datos en la base de datos
    @FXML
private void guardar(ActionEvent event) {
    // Obtener los datos de los campos de texto
    String numCompra = numc.getText();
    String idProducto = idproducto.getText();
    String totalCompra = total.getText();
    String precioProducto = precio.getText();
    String metodoPago = metodo.getText();
    String cantidadProducto = cantidad.getText();
    String folioCompra = folioc.getText();
    String costoProducto = costo.getText();  // Obtener el costo del campo

    // Validar que los campos no estén vacíos
    if (numCompra.isEmpty() || idProducto.isEmpty() || totalCompra.isEmpty() ||
        precioProducto.isEmpty() || metodoPago.isEmpty() || cantidadProducto.isEmpty() || 
        folioCompra.isEmpty() || costoProducto.isEmpty()) {

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
            // Consulta SQL para actualizar el detalle de la compra, ahora incluyendo el campo 'COSTO'
            String sql = "UPDATE detalledecompras SET IdPRODUCTOS = ?, TOTALDECOMPRA = ?, PRECIO = ?, METODODEPAGO = ?, CANTIDAD = ?, FOLIOC = ?, COSTO = ? WHERE NUMC = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);

            // Configurar los parámetros de la consulta
            stmt.setString(1, idProducto);
            stmt.setString(2, totalCompra);
            stmt.setString(3, precioProducto);
            stmt.setString(4, metodoPago);
            stmt.setString(5, cantidadProducto);
            stmt.setString(6, folioCompra);
            stmt.setString(7, costoProducto);  // Asignar el valor del costo
            stmt.setString(8, numCompra);

            // Ejecutar la actualización
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                // Si se actualizó correctamente
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Éxito");
                alert.setHeaderText("Detalle de Compra Actualizado");
                alert.setContentText("El detalle de compra ha sido actualizado exitosamente.");
                alert.showAndWait();

                // Limpiar los campos después de la actualización
                cancelar();
            } else {
                // Si hubo un error al actualizar
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error al actualizar el detalle de compra");
                alert.setContentText("Hubo un problema al intentar actualizar el detalle de compra.");
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
        numc.clear();
        idproducto.clear();
        total.clear();
        precio.clear();
        metodo.clear();
        cantidad.clear();
        folioc.clear();
    }
    
    public void setModel(detalleCompra compra) {
        this.numc.setText(String.valueOf(compra.getNumc()));
        this.idproducto.setText(String.valueOf(compra.getIdProductos()));
        this.total.setText(String.valueOf(compra.getTotalDeCompra()));
        this.precio.setText(String.valueOf(compra.getPrecio()));
        this.metodo.setText(String.valueOf(compra.getMetodoDePago()));
        this.cantidad.setText(String.valueOf(compra.getCantidad()));
        this.folioc.setText(String.valueOf(compra.getFolioC()));
    }
}
