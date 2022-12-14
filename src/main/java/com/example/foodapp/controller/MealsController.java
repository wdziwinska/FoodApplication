package com.example.foodapp.controller;

import com.example.foodapp.Main;
import com.example.foodapp.MainController;
import com.example.foodapp.api.Api;
import com.example.foodapp.api.ApiController;
import com.example.foodapp.components.Message;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;

public class MealsController {

    Api api = new Api();

    public String query = "";
    private String qValue;
    private String chosenDietValue;
    private String chosenHealthValue;
    private String chosenCuisineTypeValue;
    private String chosenMealTypeValue;
    private String chosenDishTypeValue;

    private JsonArray hits = null;
    private JsonElement url = null;
    private JsonObject zero = null;
    private JsonObject recipe = null;
    public JsonElement label = null;
    private JsonElement images = null;
    private JsonElement calories = null;
    private JsonArray ingredientLines = null;
    public String app_id = "6030db9a";
    public String app_key = "facae2fab72ad57305feb9521499cb04";

    @FXML
    private AnchorPane loading;
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

    public ArrayList<String> namesList = new ArrayList<String>();
    public ArrayList<String> urlsList = new ArrayList<String>();
    public ArrayList<String> imagesUrlList = new ArrayList<String>();;
    public ArrayList<String> caloriesList  = new ArrayList<String>();;
    public ArrayList<ArrayList> ingredientsList = new ArrayList<ArrayList>();;


    public Stage stage;

    public void onBackButtonClick(ActionEvent event) throws IOException {
        MainController mainController = new MainController();
        mainController.changeScene("main-view.fxml", event);
    }

    @FXML
    public void passInfo(ActionEvent event, String view) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(view));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(String.valueOf(getClass().getResource("styles.css")));
        RecipeController recipeController = fxmlLoader.getController();

        recipeController.getOneRecipe(namesList, ingredientsList, imagesUrlList, urlsList, caloriesList);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void getJsonObject(String qValue, String query, ActionEvent event){

        new Thread(() -> {
            try {
                RotateTransition rotate = new RotateTransition();
                rotate.setNode(loading);
                rotate.setDuration(Duration.millis(1000));
                rotate.setByAngle(360);
                rotate.setCycleCount(TranslateTransition.INDEFINITE);
                rotate.play();
                loading.setVisible(true);

                JsonObject jsonObject;
                if (qValue != null) {
                    jsonObject = ApiController.get("v2?type=public", "&q=" + qValue + "&app_id=" + app_id + "&app_key=" + app_key + query + "&imageSize=REGULAR");
                } else {
                    jsonObject = ApiController.get("v2?type=public", "&app_id=" + app_id + "&app_key=" + app_key + query + "&imageSize=REGULAR");
                }
                hits = jsonObject.getAsJsonArray("hits");
                if (hits.size() > 0) {
                    for (int i= 0 ; i<hits.size(); i++) {
                        ArrayList<String> listofIngredient = new ArrayList<String>();

                        zero = hits.get(i).getAsJsonObject();
                        recipe = zero.get("recipe").getAsJsonObject();
                        label = recipe.getAsJsonPrimitive("label");
                        images = recipe.getAsJsonPrimitive("image");
                        ingredientLines = recipe.getAsJsonArray("ingredientLines");
                        url = recipe.getAsJsonPrimitive("url");
                        calories = recipe.getAsJsonPrimitive("calories");

                        System.out.println("url: " + url);

                        for(int j = 0; j < ingredientLines.size(); j++){
                          listofIngredient.add(ingredientLines.get(j).toString().replace("\"", ""));
                        }

                        namesList.add(label.toString().replace("\"", ""));
                        imagesUrlList.add(images.toString().replace("\"", ""));
                        ingredientsList.add(i, listofIngredient);
                        urlsList.add(url.toString().replace("\"", ""));
                        caloriesList.add(calories.toString().replace("\"", ""));
                    }
                }
            }catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("mylabel: " + label);
            System.out.println("myimage: " + images);
            System.out.println("myingredientLines: " + ingredientLines);

            Platform.runLater(() ->{
                try {
                    passInfo(event, "recipes-view.fxml");
                    loading.setVisible(false);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }).start();
    }

    public void changeScene(String viewName, ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(viewName));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }


    @FXML
    public void OkButtonClick(ActionEvent event) throws IOException {
        System.out.println("Wchodze do OkButtonClick");
        qValue = mainIngredientTextField.getText();
        if (qValue != ""){
            System.out.println("q value: " + qValue);
            getJsonObject(qValue, getQuery(), event);
        }
        else{
            System.out.println("You should type main ingredient");
            Message.showPopupMessage("You should type main ingredient",(Stage)((Node)event.getSource()).getScene().getWindow());

        }

        query="";
        System.out.println("Wychodze z OkButtonClick");
    }

    public void dietMealsComboBoxAction(ActionEvent event){
        chosenDietValue = dietMealsComboBox.getValue();
        System.out.println("chosen value: " + chosenDietValue);
    }

    public void healthMealsComboBoxAction(ActionEvent event){
        chosenHealthValue = healthMealsComboBox.getValue();
        System.out.println("chosen value: " + chosenHealthValue);
    }

    public void cuisineTypeMealsComboBoxAction(ActionEvent event){
        chosenCuisineTypeValue = cuisineTypeMealsComboBox.getValue();
        System.out.println("chosen value: " + chosenCuisineTypeValue);
    }

    public void mealTypeMealsComboBoxAction(ActionEvent event){
        chosenMealTypeValue = mealTypeMealsComboBox.getValue();
        System.out.println("chosen value: " + chosenMealTypeValue);
    }
    public void dishTypeMealsComboBoxAction(ActionEvent event){
        chosenDishTypeValue = dishTypeMealsComboBox.getValue();
        System.out.println("chosen value: " + chosenDishTypeValue);
    }

    public void qMealsTextFieldAction(ActionEvent event){
        qValue = mainIngredientTextField.toString();
        System.out.println("q value: " + qValue);
    }

    public String getQuery(){

        if(chosenDietValue != null){
            query += "&diet=";
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
