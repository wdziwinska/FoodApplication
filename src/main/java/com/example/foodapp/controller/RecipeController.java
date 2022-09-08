package com.example.foodapp.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;

import java.util.List;

public class RecipeController {

    @FXML
    private Label label;
    @FXML
    private Label ingredientLines;
    @FXML
    private Label image;
    @FXML
    private ImageView imageView;
    @FXML
    private Button buttonTest;
    @FXML
    private ListView<String> listOfIngredients;


    public void getElementsToRecipe(String labelTitle, List<String> ingredient){
        System.out.println("Hello World " + labelTitle);
        label.setText(labelTitle);
        ingredientLines.setText(String.valueOf(ingredient));
        listOfIngredients.getItems().addAll(ingredient);
//        Image image = new Image(getClass().getResourceAsStream(url));
//        imageView.setImage(image);
    }

    public void onButtonTestClick(){
        System.out.println("HelloWorld");
    }
}
