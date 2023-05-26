module com.example.ejecutabke {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.ejecutabke to javafx.fxml;
    exports com.example.ejecutabke;
}