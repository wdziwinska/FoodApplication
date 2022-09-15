package com.example.foodapp;

import com.example.foodapp.api.Api;
import com.example.foodapp.api.ApiController;
import com.example.foodapp.database.DataBase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        stage.setTitle("CookBook!");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) throws SQLException, IOException {

        Api api = new Api();
        ApiController apiController = new ApiController(api);

        DataBase db = DataBase.getInstance();
        db.init();
        db.connect();

        launch();
    }
}