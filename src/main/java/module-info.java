module com.example.controldeinventario {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.controldeinventario to javafx.fxml;
    exports com.example.controldeinventario;
}