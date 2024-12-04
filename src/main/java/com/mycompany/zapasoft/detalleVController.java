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
import javafx.stage.StageStyle;
import javafx.scene.effect.GaussianBlur;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class detalleVController {

    @FXML
    private TableView<detalleVenta> tablaVentas;

    @FXML
    private TableColumn<detalleVenta, Short> numv;

    @FXML
    private TableColumn<detalleVenta, Short> empleado;

    @FXML
    private TableColumn<detalleVenta, Float> total;

    @FXML
    private TableColumn<detalleVenta, Float> precio;

    @FXML
    private TableColumn<detalleVenta, Float> costo;

    @FXML
    private TableColumn<detalleVenta, String> metodo;

    @FXML
    private TableColumn<detalleVenta, Short> cantidad;

    @FXML
    private TableColumn<detalleVenta, Short> foliov;

    @FXML
    public void initialize() {
        Connection conn = Conexion.connect();

        if (conn != null) {
            try {
                String sql = "SELECT * FROM detalledeventas";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                ObservableList<detalleVenta> detalles = FXCollections.observableArrayList();

                while (rs.next()) {
                    detalleVenta detalle = new detalleVenta(
                        rs.getShort("NUMV"),
                        rs.getShort("IdPRODUCTOS"),
                        rs.getFloat("TOTALDEVENTA"),
                        rs.getFloat("PRECIO"),
                        rs.getFloat("COSTO"),
                        rs.getString("METODODEPAGO").charAt(0),
                        rs.getShort("CANTIDAD"),
                        rs.getShort("FOLIOV")
                    );

                    detalles.add(detalle);
                }

                // Configurar columnas
                numv.setCellValueFactory(new PropertyValueFactory<>("numv"));
                empleado.setCellValueFactory(new PropertyValueFactory<>("idProductos"));
                total.setCellValueFactory(new PropertyValueFactory<>("totalDeVenta"));
                precio.setCellValueFactory(new PropertyValueFactory<>("precio"));
                costo.setCellValueFactory(new PropertyValueFactory<>("costo"));
                metodo.setCellValueFactory(new PropertyValueFactory<>("metodoDePago"));
                cantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
                foliov.setCellValueFactory(new PropertyValueFactory<>("folioV"));

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("editarDVenta.fxml"));
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("editarDVenta.fxml"));
        Parent parent = loader.load();

        int rowIndex = tablaVentas.getSelectionModel().getSelectedIndex();

        if (rowIndex != -1) {
            detalleVenta detalle = tablaVentas.getItems().get(rowIndex);

            editDVenta editController = loader.getController();
            editController.setModel(detalle);

            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setMaximized(false);
            stage.show();
        }
    }

    @FXML
private void borrar() {
    // Obtener el índice seleccionado
    int rowIndex = tablaVentas.getSelectionModel().getSelectedIndex();

    if (rowIndex != -1) {
        // Obtener el detalleVenta seleccionado
        detalleVenta detalle = tablaVentas.getItems().get(rowIndex);

        // Confirmación para borrar (opcional, aquí comentado para simplicidad)
        // Mostrar ventana de confirmación, por ejemplo, usando Alert

        // Conectar a la base de datos y borrar el registro
        Connection conn = Conexion.connect();
        if (conn != null) {
            try {
                String sql = "DELETE FROM detalledeventas WHERE NUMV = ?";
                java.sql.PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setShort(1, detalle.getNumv()); // Aquí usas la clave primaria o identificador único
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
