module com.isep.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.isep.demo to javafx.fxml;
    exports com.isep.demo;
}