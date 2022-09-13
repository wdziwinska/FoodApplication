package com.example.foodapp;

import com.example.foodapp.controller.FavouriteController;
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
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    public void mealsButtonClick(ActionEvent event) throws IOException {
        changeScene("meals-view.fxml", event);
    }

    @FXML
    protected void drinksButtonClick(ActionEvent event) throws IOException {
        changeScene("drinks-view.fxml", event);
    }

    @FXML
    protected void dessertsButtonClick(ActionEvent event) throws IOException {
        changeScene("dessert-view.fxml", event);
    }

    @FXML
    public void favouritesButtonClick(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("favourite-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(String.valueOf(getClass().getResource("styles.css")));
        FavouriteController favouriteController = fxmlLoader.getController();

        favouriteController.favMain();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        System.out.println("Favousrites clicked!!!");
    }
}