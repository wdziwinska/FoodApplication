package com.example.foodapp.controller;

import com.example.foodapp.model.Recipe;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class OneRecipeViewController implements Initializable {


    @FXML
    private ImageView image;

    @FXML
    private ListView<String> lsIngredients;

    @FXML
    private Label title;

    @FXML
    private Hyperlink hyperlink;

    public String trueUrl;

    public void setData(Recipe recipe){
        image.setImage(new Image(recipe.getImageSoruce()));
        title.setText(recipe.getName());
        lsIngredients.getItems().addAll(recipe.getIngredients());
        hyperlink.setText(recipe.getUrl());

        trueUrl = recipe.getUrl();
    }

    public void onHyperlinkClick() throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI(trueUrl.replace("\"", "")));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
