package com.example.foodapp.controller;

import com.example.foodapp.MainController;
import com.example.foodapp.api.Api;
import com.example.foodapp.api.ApiController;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class MealsController {

    Api api = new Api();
    ApiController apiController = new ApiController(api);

    public String query = "";
    private String qValue;
    private String chosenDietValue;
    private String chosenHealthValue;
    private String chosenCuisineTypeValue;
    private String chosenMealTypeValue;
    private String chosenDishTypeValue;

    private JsonArray hits = null;
    private JsonObject zero = null;
    private JsonObject recipe = null;
    private JsonElement label = null;
    private JsonElement images = null;
    private JsonArray ingredientLines = null;
    public String app_id = "6030db9a";
    public String app_key = "facae2fab72ad57305feb9521499cb04";

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

    RecipeController recipeController = new RecipeController();

    //nie moze miec konstruktora
//    public MealsController(Api api, ApiController apiController) {
//        this.api = api;
//        this.apiController = apiController;
//    }

    public Stage stage;
    MainController mainController = new MainController();

    public JsonObject getJsonObject(String qValue, String query){
        JsonObject jsonObject;
        if(qValue != null) {
            jsonObject = ApiController.get("v2?type=public", "&q=" + qValue + "&app_id=" + app_id + "&app_key=" + app_key + query + "&imageSize=REGULAR");
        }
        else {
            jsonObject = ApiController.get("v2?type=public", "&app_id=" + app_id + "&app_key=" + app_key + query + "&imageSize=REGULAR");
        }
        return jsonObject;
    }


    public void getRecipes(JsonObject jsonObject, ActionEvent event) throws IOException {

//        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("recipe-view.fxml"));
////        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        Scene scene = new Scene(fxmlLoader.load());
//
//        RecipeController controller = new RecipeController();
//        controller = fxmlLoader.getController();
//        controller.getElementsToRecipe("test");
//        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        stage.setScene(scene);
//        stage.setResizable(false);
//        stage.show();

        hits = jsonObject.getAsJsonArray("hits");
        if (hits.size() > 0) {
            zero = hits.get(0).getAsJsonObject();
            recipe = zero.get("recipe").getAsJsonObject();
            label = recipe.getAsJsonPrimitive("label");
            images = recipe.getAsJsonPrimitive("image");
            ingredientLines = recipe.getAsJsonArray("ingredientLines");
        }

        System.out.println("mylabel: " + label);
        System.out.println("myimage: " + images);
        System.out.println("myingredientLines: " + ingredientLines);

        String lebTit = label.toString();

        recipeController.getElementsToRecipe(lebTit);
    }

    @FXML
    protected void recipeAnchorPane(ActionEvent event) throws IOException {
        OkButtonClick();
        mainController.changeScene("recipe-view.fxml", event);
    }

    public void OkButtonClick() throws IOException {
        qValue = mainIngredientTextField.getText();
        System.out.println("q value: " + qValue);
        MainController mainController = new MainController();
        ActionEvent event = new ActionEvent();
        getRecipes(getJsonObject(qValue, getQuery()), event);
        query="";
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
