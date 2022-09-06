package com.example.foodapplication.controller;

import com.example.foodapplication.api.Api;
import com.example.foodapplication.api.ApiController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MealsController {

    com.example.foodapplication.api.Api api = new Api();
    com.example.foodapplication.api.ApiController apiController = new ApiController(api);

    public String query = "&";
    private String qValue;
    private String chosenDietValue;
    private String chosenHealthValue;
    private String chosenCuisineTypeValue;
    private String chosenMealTypeValue;
    private String chosenDishTypeValue;

    @FXML
    private Label labelTest;

    @FXML
    private ComboBox<String> dietMealsComboBox;

    @FXML
    private ComboBox<String> healthMealsComboBox;

    @FXML
    private ComboBox<String> cuisineTypeMealsComboBox;
    @FXML
    private ComboBox<String> mealTypeMealsComboBox;
    @FXML
    private ComboBox<String> dishTypeMealsComboBox;
    @FXML
    private TextField mainIngredientTextField;

    public void OkButtonClick(){
        qValue = mainIngredientTextField.getText();
        System.out.println("q value: " + qValue);
        getQuery();
    }

    public void dietMealsComboBoxAction(ActionEvent event){
        labelTest.setText(dietMealsComboBox.getValue());
        chosenDietValue = dietMealsComboBox.getValue();
        System.out.println("chosen value: " + chosenDietValue);
    }

    public void healthMealsComboBoxAction(ActionEvent event){
        labelTest.setText(healthMealsComboBox.getValue());
        chosenHealthValue = healthMealsComboBox.getValue();
        System.out.println("chosen value: " + chosenHealthValue);
    }

    public void cuisineTypeMealsComboBoxAction(ActionEvent event){
        labelTest.setText(cuisineTypeMealsComboBox.getValue());
        chosenCuisineTypeValue = cuisineTypeMealsComboBox.getValue();
        System.out.println("chosen value: " + chosenCuisineTypeValue);
    }

    public void mealTypeMealsComboBoxAction(ActionEvent event){
        labelTest.setText(mealTypeMealsComboBox.getValue());
        chosenMealTypeValue = mealTypeMealsComboBox.getValue();
        System.out.println("chosen value: " + chosenMealTypeValue);
    }
    public void dishTypeMealsComboBoxAction(ActionEvent event){
        labelTest.setText(dishTypeMealsComboBox.getValue());
        chosenDishTypeValue = dishTypeMealsComboBox.getValue();
        System.out.println("chosen value: " + chosenDishTypeValue);
    }

    public void qMealsTextFieldAction(ActionEvent event){
        qValue = mainIngredientTextField.toString();
        System.out.println("q value: " + qValue);
    }

    public String getQuery(){

        if(chosenDietValue != null){
            query += "diet=";
            query += chosenDietValue;
        }
        if(chosenHealthValue != null){
            query += "&health=";
            query += chosenHealthValue;
        }
        if(chosenCuisineTypeValue != null){
            query += "&cuisineType=";
            query += chosenCuisineTypeValue;
        }
        if(chosenMealTypeValue != null){
            query += "&mealType=";
            query += chosenMealTypeValue;
        }
        if(chosenDishTypeValue != null){
            query += "&dishType=";
            query += chosenDishTypeValue;
        }
        System.out.println(query);
        return query;
    }
}
