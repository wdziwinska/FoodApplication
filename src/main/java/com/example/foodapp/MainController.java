package com.example.foodapp;

import com.example.foodapp.api.ApiController;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    public Stage stage;
    public String app_id = "6030db9a";
    public String app_key = "facae2fab72ad57305feb9521499cb04";

    private JsonArray hits = null;
    private JsonObject zero = null;
    private JsonObject recipe = null;
    private JsonElement label = null;
    private JsonElement images = null;
    private JsonArray ingredientLines = null;
    public JsonObject jsonObject = null;

    private void changeScene(String viewName, ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(viewName));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    public void mealsButtonClick(ActionEvent event) throws IOException {
        changeScene("meals-view.fxml", event);
    }

    @FXML
    protected void drinkAnchorPane(ActionEvent event) throws IOException {
        changeScene("drink-view.fxml", event);
    }

    @FXML
    protected void sweatsAnchorPane(ActionEvent event) throws IOException {
        changeScene("dessert-view.fxml", event);
    }

    public void getRecipes(String qValue, String query){

        if(qValue != null) {
            jsonObject = ApiController.get("v2?type=public", "&q=" + qValue + "&app_id=" + app_id + "&app_key=" + app_key + query + "&imageSize=REGULAR");
//        JsonObject jsonObject = ApiController.get("recipes", "v2?type=public&q=" + q + "&app_id=" + app_id +"&app_key=" + app_key + "&diet=" + diet
//                + "&health=" + health + "&cuisineType=" + cuisineType + "&mealType=" + mealType + "&dishType=" + dishType);
//            System.out.println(jsonObject);
        }
        else {
            jsonObject = ApiController.get("v2?type=public", "&app_id=" + app_id + "&app_key=" + app_key + query + "&imageSize=REGULAR");

        }

        hits = jsonObject.getAsJsonArray("hits");
        if (hits.size() > 0) {
            zero = hits.get(0).getAsJsonObject();
            recipe = zero.get("recipe").getAsJsonObject();
            label = recipe.getAsJsonPrimitive("label");
            images = recipe.getAsJsonPrimitive("image");
            ingredientLines = recipe.getAsJsonArray("ingredientLines");
        }

        System.out.println("myhits: " + hits);
        System.out.println("myrecipe: " + recipe);
        System.out.println("mylabel: " + label);
        System.out.println("myimage: " + images);
        System.out.println("myingredientLines: " + ingredientLines);
    }
}