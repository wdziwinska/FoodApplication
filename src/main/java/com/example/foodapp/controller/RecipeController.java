package com.example.foodapp.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

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


    public void getElementsToRecipe(String labelTitle){
        System.out.println("Hello World " + labelTitle);
        label.setText(labelTitle);
    }

    public void onButtonTestClick(){
        System.out.println("HelloWorld");
    }
}
