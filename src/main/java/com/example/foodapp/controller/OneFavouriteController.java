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
import javafx.scene.layout.HBox;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OneFavouriteController implements Initializable {

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
    @FXML
    private HBox hBoxData;

    public String trueUrl;

    public String nameChosen;
    public String imageUrlChosen;
    public ArrayList<String> ingredientsChosen;
    public String caloriesChosen;
    public String urlChosen;

    DataBase db= DataBase.getInstance();

    public void setData(Recipe recipe){
        System.out.println("... ustawiam danne w SetData xd");
        image.setImage(new Image(recipe.getImageSoruce()));
        title.setText(recipe.getName());
        lsIngredients.getItems().addAll(recipe.getIngredients());

//        for (int i=0; i<recipe.getIngredients().size(); i++){
//            lsIngredients.getItems().add(i, recipe.getIngredients().toString());
//        }

        hyperlink.setText(recipe.getUrl());
        calories.setText(recipe.getCalories());

        trueUrl = recipe.getUrl();

        nameChosen = recipe.getName();
        imageUrlChosen = recipe.getImageSoruce();
//        ingredientsChosen = recipe.getIngredients();
        caloriesChosen = recipe.getCalories();
        urlChosen = recipe.getUrl();
        System.out.println("... ustawiono danne w SetData xd");
    }

    public void onHyperlinkClick() throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI(trueUrl.replace("\"", "")));
    }

    @FXML
    public void onDeleteButtonClick() throws SQLException {

        System.out.println("NAcisnieto delete");
        System.out.println("wybrano: " + nameChosen);

        db.delete("meals", nameChosen);
        db.delete("meals", lsIngredients.toString());
        db.delete("meals", imageUrlChosen);
        db.delete("meals", urlChosen);
        db.delete("meals", caloriesChosen);


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
