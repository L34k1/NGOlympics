module com.isep.tentative {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.postgresql.jdbc;

    opens com.isep.tentative to javafx.fxml;
    exports com.isep.tentative;
}