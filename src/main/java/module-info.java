module com.mycompany.zapasoft {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.zapasoft to javafx.fxml;
    exports com.mycompany.zapasoft;
}
