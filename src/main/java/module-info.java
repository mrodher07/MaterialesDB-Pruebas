module com.example.materialesdb {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.mariadb.jdbc;


    opens com.example.materialesdb to javafx.fxml;
    exports com.example.materialesdb;
    exports com.example.materialesdb.dao;
    exports com.example.materialesdb.modelos;
}