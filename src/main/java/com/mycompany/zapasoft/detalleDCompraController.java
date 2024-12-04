package com.mycompany.zapasoft;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class detalleDCompraController {

    @FXML
    private TableView<detalleCompra> tablaVentas;

    @FXML
    private TableColumn<detalleCompra, Short> numv;

    @FXML
    private TableColumn<detalleCompra, Float> totalDeCompra;

    @FXML
    private TableColumn<detalleCompra, Float> precio;

    @FXML
    private TableColumn<detalleCompra, Float> costo;

    @FXML
    private TableColumn<detalleCompra, String> metodoDePago;

    @FXML
    private TableColumn<detalleCompra, Short> cantidad;

    @FXML
    private TableColumn<detalleCompra, Short> idProductos;

    @FXML
    private TableColumn<detalleCompra, Short> folioC;

    @FXML
    public void initialize() {
        Connection conn = Conexion.connect();

        if (conn != null) {
            try {
                String sql = "SELECT * FROM detalledecompras";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                ObservableList<detalleCompra> detalles = FXCollections.observableArrayList();

                while (rs.next()) {
                    detalleCompra detalle = new detalleCompra(
                        rs.getShort("NUMC"),
                        rs.getFloat("TOTALDECOMPRA"),
                        rs.getFloat("PRECIO"),
                        rs.getFloat("COSTO"),
                        rs.getString("METODODEPAGO"),
                        rs.getShort("CANTIDAD"),
                        rs.getShort("IdPRODUCTOS"),
                        rs.getShort("FOLIOC")
                    );

                    detalles.add(detalle);
                }

                // Configurar columnas
                numv.setCellValueFactory(new PropertyValueFactory<>("numc"));
                totalDeCompra.setCellValueFactory(new PropertyValueFactory<>("totalDeCompra"));
                precio.setCellValueFactory(new PropertyValueFactory<>("precio"));
                costo.setCellValueFactory(new PropertyValueFactory<>("costo"));
                metodoDePago.setCellValueFactory(new PropertyValueFactory<>("metodoDePago"));
                cantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
                idProductos.setCellValueFactory(new PropertyValueFactory<>("idProductos"));
                folioC.setCellValueFactory(new PropertyValueFactory<>("folioC"));

                // Asignar datos a la tabla
                tablaVentas.setItems(detalles);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No se pudo conectar a la base de datos.");
        }
    }

    @FXML
    private void agregar() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("agregarDCompra.fxml"));
        Parent parent = loader.load();

        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setMaximized(false);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
private void editar() throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("editarDCompra.fxml"));
    Parent parent = loader.load();

    // Obtener la fila seleccionada de la tabla
    int rowIndex = tablaVentas.getSelectionModel().getSelectedIndex();

    if (rowIndex != -1) {
        // Obtener el objeto detalleCompra seleccionado
        detalleCompra detalle = tablaVentas.getItems().get(rowIndex);

        // Pasar los datos al controlador de la ventana de edición
        editarDetalleCompra editController = loader.getController();
        editController.setModel(detalle);

        // Configurar la nueva ventana de edición
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setMaximized(false);
        stage.show();
    } else {
        System.out.println("No se ha seleccionado ninguna fila.");
    }
}

    @FXML
    private void borrar() {
    // Obtener el índice seleccionado
    int rowIndex = tablaVentas.getSelectionModel().getSelectedIndex();

    if (rowIndex != -1) {
        // Obtener el detalleCompra seleccionado
        detalleCompra detalle = tablaVentas.getItems().get(rowIndex);

        // Confirmación para borrar (opcional, aquí comentado para simplicidad)
        // Mostrar ventana de confirmación, por ejemplo, usando Alert

        // Conectar a la base de datos y borrar el registro
        Connection conn = Conexion.connect();
        if (conn != null) {
            try {
                String sql = "DELETE FROM detalledecompras WHERE NUMC = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, String.valueOf(detalle.getNumc()));
                pstmt.executeUpdate();

                // Eliminar el ítem de la tabla
                tablaVentas.getItems().remove(rowIndex);

                System.out.println("Registro eliminado correctamente.");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error al eliminar el registro de la base de datos.");
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("No se pudo conectar a la base de datos.");
        }
    } else {
        System.out.println("No se ha seleccionado ningún elemento para borrar.");
    }
}


    @FXML
    private void actualizarTabla() {
        initialize();
    }
}
