module com.isep.demo1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.isep.demo1 to javafx.fxml;
    exports com.isep.demo1;
}