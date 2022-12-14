module com.example.foodapplication {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.google.gson;
    requires org.apache.httpcomponents.httpcore;
    requires org.apache.httpcomponents.httpclient;
    requires java.logging;
    requires java.sql;
    requires mysql.connector.java;
    requires java.datatransfer;
    requires lombok;
    requires java.desktop;

    opens com.example.foodapp to javafx.fxml;
    exports com.example.foodapp;
    exports com.example.foodapp.controller;
    opens com.example.foodapp.controller to javafx.fxml;
}