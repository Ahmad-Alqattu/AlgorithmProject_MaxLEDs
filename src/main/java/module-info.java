module com.example.LEDs {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.LEDs to javafx.fxml;
    exports com.example.LEDs;
}