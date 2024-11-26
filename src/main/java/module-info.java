module com.mycompany.zapasoft {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.mycompany.zapasoft to javafx.fxml;
    exports com.mycompany.zapasoft;
}
