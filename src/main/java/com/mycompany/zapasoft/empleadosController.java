package com.mycompany.zapasoft;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.GaussianBlur;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class empleadosController {
    
    @FXML
    private TableView<Empleado> tablaEmpleado;
    @FXML
    private TableColumn<Empleado, String> columnaID;
    @FXML
    private TableColumn<Empleado, String> columnaNombre;
    @FXML
    private TableColumn<Empleado, String> columnaContraseña;
    @FXML
    private TableColumn<Empleado, String> columnaHorarioE;
    @FXML
    private TableColumn<Empleado, String> columnaHorarioS;
    @FXML
    private TableColumn<Empleado, String> columnaTelefono;
    @FXML
    private TableColumn<Empleado, String> columnaApellidos;
    @FXML
    private TableColumn<Empleado, String> columnaCorreo;
    @FXML
    private TableColumn<Empleado, String> columnaPuesto;
    
    @FXML
    public void initialize() {
        
        Connection conn = Conexion.connect();

if (conn != null) {
    try {
        String sql = "SELECT * FROM empleados";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        ObservableList<Empleado> empleados = FXCollections.observableArrayList();

        while (rs.next()) {
            Empleado empleado = new Empleado();
            empleado.setId(rs.getInt("IdEMPLEADOS"));
            empleado.setNombre(rs.getString("Nombres"));
            empleado.setTelefono(rs.getString("Teléfono"));
            empleado.setApellido(rs.getString("Apellidos"));
            empleado.setCorreo(rs.getString("Correo"));
            empleado.setPuesto(rs.getString("Puesto"));
            empleado.setHorarioe(rs.getTime("HorarioEntrada"));
            empleado.setHorarios(rs.getTime("HorarioSalida"));
            empleado.setContraseña(rs.getInt("Contraseña"));

            empleados.add(empleado);
        }

        columnaID.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        columnaApellidos.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        columnaCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
        columnaPuesto.setCellValueFactory(new PropertyValueFactory<>("puesto"));
        columnaContraseña.setCellValueFactory(new PropertyValueFactory<>("contraseña"));
        columnaHorarioE.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getHorarioe().toString()));
        columnaHorarioS.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getHorarios().toString()));

        tablaEmpleado.setItems(empleados);

    } catch (SQLException e) {
        e.printStackTrace();
    }
    } else {
        System.out.println("No se pudo conectar a la base de datos.");
    }

    }
    
    @FXML
    private void agregar() throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("agregarEmpleado.fxml"));
    Parent parent = loader.load();

    Scene scene = new Scene(parent);
    Stage stage = new Stage();
    stage.initModality(Modality.APPLICATION_MODAL);
    stage.initOwner(stage.getOwner());
    stage.setResizable(false);
    stage.setMaximized(false);
    stage.setScene(scene);
    stage.show();
}

    
    @FXML
    private void editar() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("editarEmpleado.fxml"));
        Parent parent = loader.load();

        int rowIndex = tablaEmpleado.getSelectionModel().getSelectedIndex();

        if(rowIndex != -1){
        Empleado empleado = tablaEmpleado.getItems().get(rowIndex);

        editarEmpleadoController editController = loader.getController();
        editController.setModel(empleado);

        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(stage.getOwner());
        stage.setMaximized(false);
        stage.setResizable(false);

        stage.show();
        }
    }

    @FXML
    private void borrar() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("confirmacionEmpleado.fxml"));
        Parent parent = loader.load();

        int rowIndex = tablaEmpleado.getSelectionModel().getSelectedIndex();
        if(rowIndex != -1){
        Empleado empleado = tablaEmpleado.getItems().get(rowIndex);

        confirmacionEmpleadoController confirmacionController = loader.getController();
        confirmacionController.setEmpleado(empleado);

            GaussianBlur blurEffect = new GaussianBlur();
            blurEffect.setRadius(10);
            Stage mainStage = (Stage) tablaEmpleado.getScene().getWindow();
            mainStage.getScene().getRoot().setEffect(blurEffect);

        Stage stage = new Stage(StageStyle.UNDECORATED);
        stage.setScene(new Scene(parent));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(stage.getOwner());
        stage.setMaximized(false);
        stage.setResizable(false);

            stage.setOnHiding(event -> mainStage.getScene().getRoot().setEffect(null));

        stage.show();
        }
    }
    
    @FXML
private void actualizarTabla() {
    Connection conn = Conexion.connect();

    if (conn != null) {
        try {
            String sql = "SELECT * FROM empleados"; // Cambia el nombre de la tabla si es necesario
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            ObservableList<Empleado> empleados = FXCollections.observableArrayList();

            while (rs.next()) {
                Empleado empleado = new Empleado();
                empleado.setId(rs.getInt("IdEMPLEADOS"));
                empleado.setNombre(rs.getString("Nombres"));
                empleado.setTelefono(rs.getString("Teléfono"));
                empleado.setApellido(rs.getString("Apellidos"));
                empleado.setCorreo(rs.getString("Correo"));
                empleado.setPuesto(rs.getString("Puesto"));
                empleado.setHorarioe(rs.getTime("HorarioEntrada"));
                empleado.setHorarios(rs.getTime("HorarioSalida"));
                empleado.setContraseña(rs.getInt("Contraseña"));

                empleados.add(empleado);
            }

            columnaID.setCellValueFactory(new PropertyValueFactory<>("id"));
            columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            columnaTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
            columnaApellidos.setCellValueFactory(new PropertyValueFactory<>("apellido"));
            columnaCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
            columnaPuesto.setCellValueFactory(new PropertyValueFactory<>("puesto"));
            columnaContraseña.setCellValueFactory(new PropertyValueFactory<>("contraseña"));
            columnaHorarioE.setCellValueFactory(cellData -> 
                new SimpleStringProperty(cellData.getValue().getHorarioe().toString()));
            columnaHorarioS.setCellValueFactory(cellData -> 
                new SimpleStringProperty(cellData.getValue().getHorarios().toString()));
            
            tablaEmpleado.setItems(empleados);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    } else {
        System.out.println("No se pudo conectar a la base de datos.");
    }
    }

    
}
