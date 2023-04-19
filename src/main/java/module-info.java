module com.example.controldeinventario {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires barcode4j;
    requires java.desktop;
    requires itext5.itextpdf;

    opens com.example.controldeinventario.Datos to javafx.base;
    opens com.example.controldeinventario to javafx.fxml;
    exports com.example.controldeinventario;
}