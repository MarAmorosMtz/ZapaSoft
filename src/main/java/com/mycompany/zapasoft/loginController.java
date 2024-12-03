package com.mycompany.zapasoft;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class loginController {

    @FXML
    private TextField user;
    @FXML
    private TextField pass;

    @FXML
    public void handleLogin() throws IOException {
        String username = user.getText();
        String password = pass.getText();

        if (authenticate(username, password)) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("dashBoard.fxml"));
            Parent parent = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.initOwner(stage.getOwner());
            stage.setMaximized(true);
            stage.setResizable(true);
            
            Stage stage1 = (Stage)user.getScene().getWindow();
            stage1.close();
            
            stage.show();
        } else {
            showAlert("Error", "Usuario o contraseña incorrectos", Alert.AlertType.ERROR);
        }
    }

    private boolean authenticate(String username, String password) {
        String url = "jdbc:mysql://localhost:3306/zapateria";
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            return true;
        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
            return false;
        }
    }

    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
