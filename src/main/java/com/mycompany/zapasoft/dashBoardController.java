package com.mycompany.zapasoft;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;


public class dashBoardController {
    
    @FXML
    private AnchorPane vista;
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        Button clickedButton = (Button) event.getSource();

        String fxml = getViewForButton(clickedButton);
        if (fxml != null) {
            loadView(fxml);
        }
    }

    private String getViewForButton(Button button){
        switch (button.getId()) {
            case "proveedores":
                return "proveedores";
            case "empleados":
                return "empleados";
            case "compras":
                return "";
            case "ventas":
                return "";
            case "productos":
                return "";
            default:
                return null;
        }
    }


    private void loadView(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml + ".fxml"));
        Parent nuevaVista = loader.load();

        vista.getChildren().clear();
        vista.getChildren().add(nuevaVista);
        AnchorPane.setTopAnchor(nuevaVista, 5.0);
        AnchorPane.setBottomAnchor(nuevaVista, 5.0);
        AnchorPane.setLeftAnchor(nuevaVista, 5.0);
        AnchorPane.setRightAnchor(nuevaVista, 5.0);
    }
}
