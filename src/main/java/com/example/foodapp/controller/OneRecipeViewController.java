package com.example.foodapp.controller;

import com.example.foodapp.database.DataBase;
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
import java.sql.SQLException;
import java.util.ArrayList;
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

    @FXML
    private Label calories;

    public String trueUrl;

    public String nameChosen;
    public String imageUrlChosen;
    public ArrayList<String> ingredientsChosen;
    public String caloriesChosen;
    public String urlChosen;

    public void setData(Recipe recipe){
        image.setImage(new Image(recipe.getImageSoruce()));
        title.setText(recipe.getName());
        lsIngredients.getItems().addAll(recipe.getIngredients());
        hyperlink.setText(recipe.getUrl());
        calories.setText(recipe.getCalories());

        trueUrl = recipe.getUrl();

        nameChosen = recipe.getName();
        imageUrlChosen = recipe.getImageSoruce();
        ingredientsChosen = recipe.getIngredients();
        caloriesChosen = recipe.getCalories();
        urlChosen = recipe.getUrl();
    }

    public void onHyperlinkClick() throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI(trueUrl.replace("\"", "")));
    }

    public void addToFavourites() throws SQLException {
        System.out.println("Dodano do ulubionych");
        System.out.println("wybrano: " + nameChosen);
        System.out.println("wybrano: " + imageUrlChosen);
        System.out.println("wybrano: " + ingredientsChosen);
        System.out.println("wybrano: " + caloriesChosen);
        System.out.println("wybrano: " + urlChosen);

        DataBase db = DataBase.getInstance();
        db.insertIntoMeals(nameChosen, imageUrlChosen, ingredientsChosen, caloriesChosen, urlChosen);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
