package com.example.foodapp.controller;

import com.example.foodapp.model.Recipe;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class OneRecipeViewController implements Initializable {


    @FXML
    private ImageView image;

    @FXML
    private ListView<String> lsIngredients;

    @FXML
    private Label title;

    public void setData(Recipe recipe){
        image.setImage(new Image(recipe.getImageSoruce()));
        title.setText(recipe.getName());
        lsIngredients.getItems().addAll(recipe.getIngredients());

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
