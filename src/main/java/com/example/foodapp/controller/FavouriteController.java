package com.example.foodapp.controller;

import com.example.foodapp.Main;
import com.example.foodapp.MainController;
import com.example.foodapp.database.DataBase;
import com.example.foodapp.model.Recipe;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FavouriteController implements Initializable {

    @FXML
    private VBox recipesLayout;

    public ArrayList<String> name = new ArrayList<>();
    public ArrayList<String> imageUrl = new ArrayList<>();
    public ArrayList<String> urlsRecipe = new ArrayList<>();
    public ArrayList<String> calories= new ArrayList<>();
    public ArrayList<String> lsOfIngredients = new ArrayList<>();

    ResultSet meals = null;

    DataBase db = DataBase.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void favMain(){
        System.out.println(".. wchodze do favMain");
        new Thread(()->{
            try {
                getMeals();
            } catch (Exception e){
                e.printStackTrace();
            }
        }).start();
//        Platform.runLater(() ->{
//            getOneFav();
//        });
        System.out.println(".. wychodze z favMain");
    }

    public void getOneFav(){

        System.out.println(".. wchodze do getOneFav");
        List<Recipe> recipes = new ArrayList<>(recipes());

        Platform.runLater(() ->{
            for (int i = 0; i<recipes.size(); i++){
                FXMLLoader FXMLloader = new FXMLLoader(Main.class.getResource("oneFavourite-view.fxml"));

                try {
                    HBox hBox = FXMLloader.load();
                    OneFavouriteController oneFavouriteController = FXMLloader.getController();
                    oneFavouriteController.setData(recipes.get(i));
                    recipesLayout.getChildren().add(hBox);

                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        });

        System.out.println(".. wychodze z getOneFav");
    }

    private List<Recipe> recipes(){
        System.out.println(".. whodze do z recipes");
        List<Recipe> listRecipes = new ArrayList<>();

        System.out.println("image url: " + imageUrl);

        for(int i=0; i<name.size(); i++) {
            Recipe recipe = new Recipe();
            recipe.setName(name.get(i));
            recipe.setIngredient(lsOfIngredients);
            recipe.setImageSoruce(imageUrl.get(i));
            recipe.setUrl(urlsRecipe.get(i));
            recipe.setCalories(calories.get(i).split("\\.")[0]);

            listRecipes.add(recipe);
        }
        System.out.println(".. wychodze z recipes");
        return listRecipes;
    }

    public void getMeals() throws SQLException {
        System.out.println(".. wchodze do getMeals");
        meals = db.getAll("meals");
        int i=0;

        while (meals.next()){
            System.out.println("test db: " + meals.getString("name"));
//        for(int i=0; i<meals.getFetchSize(); i++){
            name.add(meals.getString("name"));
            lsOfIngredients.add(meals.getString("ingredients"));
            imageUrl.add(meals.getString("imageUrl"));
            calories.add(meals.getString("calories"));
            urlsRecipe.add(meals.getString("urlRecipe"));
        }
        System.out.println(".. wychodze z getMeals");
        getOneFav();
    }

    public void onBackButtonClick(ActionEvent event) throws IOException {
        MainController mainController = new MainController();
        mainController.changeScene("main-view.fxml", event);
    }
}