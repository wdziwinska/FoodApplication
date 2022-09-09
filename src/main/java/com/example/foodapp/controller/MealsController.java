package com.example.foodapp.controller;

import com.example.foodapp.Main;
import com.example.foodapp.MainController;
import com.example.foodapp.api.Api;
import com.example.foodapp.api.ApiController;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

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
    private JsonElement url = null;
    private JsonObject zero = null;
    private JsonObject recipe = null;
    public JsonElement label = null;
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

    public String path;

    public ArrayList<String> names = new ArrayList<String>();
    public ArrayList<String> urls = new ArrayList<String>();
    public ArrayList<String> imagesUrl  = new ArrayList<String>();;
    public ArrayList<ArrayList> ingredients  = new ArrayList<ArrayList>();;

    //nie moze miec konstruktora
//    public MealsController(Api api, ApiController apiController) {
//        this.api = api;
//        this.apiController = apiController;
//    }

    public Stage stage;
    MainController mainController = new MainController();
    String newUrl;

    public void onBackButtonClick(ActionEvent event) throws IOException {
        MainController mainController = new MainController();
        mainController.changeScene("hello-view.fxml", event);
    }

    @FXML
    public void passInfo(ActionEvent event, String view) throws IOException {
        System.out.println("Wchodze do passInfo");
        FXMLLoader FXMLloader = new FXMLLoader(Main.class.getResource(view));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(FXMLloader.load());
        RecipeController recipeController = new RecipeController();

        recipeController = FXMLloader.getController();
        recipeController.getOneRecipe(names, ingredients, imagesUrl, urls);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        System.out.println("Wychodze z passInfo");

        ////        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("recipes-view.fxml"));
//////        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
////        Scene scene = new Scene(fxmlLoader.load());
////
////        RecipeController controller = new RecipeController();
////        controller = fxmlLoader.getController();
////        controller.getElementsToRecipe("test");
////        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
////        stage.setScene(scene);
////        stage.setResizable(false);
////        stage.show();
    }

    public void getJsonObject(String qValue, String query, ActionEvent event){

        System.out.println("Wchodze do getJesonObject");
        new Thread(() -> {
            try {
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

                        System.out.println("url: " + url);

                        for(int j = 0; j < ingredientLines.size(); j++){
                          listofIngredient.add(ingredientLines.get(j).toString());
                        }
                        path = images.toString();
                        newUrl = path.replace("\"", "");

                        names.add(label.toString());
                        imagesUrl.add(newUrl);
                        ingredients.add(i, listofIngredient);
                        urls.add(url.toString());
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
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            System.out.println("Wychodze z getJesonObject");
//            recipeController.getElementsToRecipe(lebTit);
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

//    @FXML
//    protected void recipeAnchorPane(ActionEvent event) throws IOException {
//        OkButtonClick(event);
////        changeScene("recipes-view.fxml", event);
//    }

    public void OkButtonClick(ActionEvent event) throws IOException {
        System.out.println("Wchodze do OkButtonClick");
        qValue = mainIngredientTextField.getText();
        System.out.println("q value: " + qValue);
        getJsonObject(qValue, getQuery(), event);
        query="";
        System.out.println("Wychodze z OkButtonClick");
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
