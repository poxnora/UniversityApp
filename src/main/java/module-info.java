module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.persistence;
    requires java.sql;
    requires org.hibernate.orm.core;
    requires org.apache.pdfbox;
    opens com.example.demo;
    exports com.example.demo;
    opens com.example.demo.controller;
    exports com.example.demo.controller;
    opens com.example.demo.controller.admin;
    exports com.example.demo.controller.admin;
    opens com.example.demo.controller.prowadzacy;
    exports com.example.demo.controller.prowadzacy;
    opens com.example.demo.controller.student;
    exports com.example.demo.controller.student;
    exports com.example.demo.auth;
    opens com.example.demo.auth;
    exports com.example.demo.entity;
    exports com.example.demo.data;
    opens com.example.demo.data;
}