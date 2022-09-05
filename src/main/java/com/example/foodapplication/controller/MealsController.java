package com.example.foodapplication.controller;

import com.example.foodapplication.api.Api;
import com.example.foodapplication.api.ApiController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class MealsController {

    com.example.foodapplication.api.Api api = new Api();
    com.example.foodapplication.api.ApiController apiController = new ApiController(api);

    @FXML
    private Label labelTest;

    @FXML
    private ComboBox<String> dietMealsComboBox;

    public void dietMealsComboBoxAction(ActionEvent event){
        labelTest.setText(dietMealsComboBox.getValue());
    }
}
