package com.example.foodapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    public Stage stage;

    public void changeScene(String viewName, ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(viewName));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    public void mealsButtonClick(ActionEvent event) throws IOException {
        changeScene("meals-view.fxml", event);
    }

    @FXML
    protected void drinkAnchorPane(ActionEvent event) throws IOException {
        changeScene("drink-view.fxml", event);
    }

    @FXML
    protected void sweatsAnchorPane(ActionEvent event) throws IOException {
        changeScene("dessert-view.fxml", event);
    }
}