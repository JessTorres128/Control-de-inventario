module com.example.controldeinventario {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires barcode4j;
    requires java.desktop;
    requires itext5.itextpdf;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;
    requires org.apache.logging.log4j.core;
    requires org.apache.logging.log4j;

    opens com.example.controldeinventario.Datos to javafx.base;
    opens com.example.controldeinventario to javafx.fxml;
    exports com.example.controldeinventario;
}