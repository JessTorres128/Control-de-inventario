module com.example.controldeinventario {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.controldeinventario to javafx.fxml;
    exports com.example.controldeinventario;
}