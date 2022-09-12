package com.example.foodapp.controller;

import com.example.foodapp.Main;
import com.example.foodapp.MainController;
import com.example.foodapp.model.Recipe;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class RecipeController implements Initializable {

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
    @FXML
    private VBox recipesLayout;
    @FXML
    private AnchorPane loading;

    public ArrayList<String> name;
    public ArrayList<String> imageUrl;
    public ArrayList<String> urlsRecipe;
    public ArrayList<String> calories;
    public ArrayList<ArrayList> lsOfIngredients;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void getOneRecipe(ArrayList<String> labelTitle, ArrayList<ArrayList> ingredient, ArrayList<String> url, ArrayList<String> urls, ArrayList<String> caloriesList){

        name = labelTitle;
        imageUrl = url;
        lsOfIngredients = ingredient;
        urlsRecipe = urls;
        calories = caloriesList;

        RotateTransition rotate = new RotateTransition();
        rotate.setNode(loading);
        rotate.setDuration(Duration.millis(1000));
        rotate.setByAngle(360);
        rotate.setCycleCount(TranslateTransition.INDEFINITE);
        rotate.play();
        loading.setVisible(true);

        List<Recipe> recipes = new ArrayList<>(recipes());

        Platform.runLater(() ->{
            for (int i = 0; i<recipes.size(); i++){
//                FXMLLoader FXMLloader = new FXMLLoader();
//                FXMLloader.setLocation(getClass().getResource("oneRecipe-view.fxml"));
                FXMLLoader FXMLloader = new FXMLLoader(Main.class.getResource("oneRecipe-view.fxml"));

                try {
                    HBox hBox = FXMLloader.load();
                    OneRecipeViewController oneRecipeViewController = FXMLloader.getController();
                    oneRecipeViewController.setData(recipes.get(i));
                    recipesLayout.getChildren().add(hBox);
                    loading.setVisible(false);

                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        });
    }

    private List<Recipe> recipes(){
        List<Recipe> listRecipes = new ArrayList<>();

        System.out.println("image url: " + imageUrl);

        for(int i=0; i<name.size(); i++) {
            Recipe recipe = new Recipe();
            recipe.setName(name.get(i));
            recipe.setIngredients(lsOfIngredients.get(i));
            recipe.setImageSoruce(imageUrl.get(i));
            recipe.setUrl(urlsRecipe.get(i));
//            recipe.setCalories(calories.get(i));
            recipe.setCalories(calories.get(i).split("\\.")[0]);
//            recipe.setCalories(calories.get(i).slice(0, calories.get(i).lastIndexOf(".")));

            listRecipes.add(recipe);
        }
        return listRecipes;
    }

    public void onBackButtonClick(ActionEvent event) throws IOException {
        MainController mainController = new MainController();
        mainController.changeScene("meals-view.fxml", event);
    }
}