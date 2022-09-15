package com.example.foodapp.controller;

import com.example.foodapp.Main;
import com.example.foodapp.MainController;
import com.example.foodapp.database.DataBase;
import com.example.foodapp.model.Recipe;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FavouriteController implements Initializable {


    @FXML
    private AnchorPane loading;
    @FXML
    private VBox recipesLayout;

    public ArrayList<String> name = new ArrayList<>();
    public ArrayList<String> imageUrl = new ArrayList<>();
    public ArrayList<String> urlsRecipe = new ArrayList<>();
    public ArrayList<String> calories= new ArrayList<>();
    public ArrayList<ArrayList> lsOfIngredientsArrayList = new ArrayList<>();
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
                RotateTransition rotate = new RotateTransition();
                rotate.setNode(loading);
                rotate.setDuration(Duration.millis(1000));
                rotate.setByAngle(360);
                rotate.setCycleCount(TranslateTransition.INDEFINITE);
                rotate.play();
                loading.setVisible(true);

                getMealsFromDB();
            } catch (Exception e){
                e.printStackTrace();
            }
            Platform.runLater(() ->{
//                try {
                    getOneFav();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                loading.setVisible(false);
            });
        }).start();
        System.out.println(".. wychodze z favMain");
    }

    public void getOneFav() {

        System.out.println(".. wchodze do getOneFav");
        List<Recipe> recipes = new ArrayList<>(recipes());
        loading.setVisible(false);

//        Platform.runLater(() ->{
            for (int i = 0; i<recipes.size(); i++){
                FXMLLoader FXMLloader = new FXMLLoader(Main.class.getResource("oneFavourite-view.fxml"));
             //   Scene scene = new Scene(FXMLloader.load());
               // scene.getStylesheets().add(String.valueOf(getClass().getResource("styles.css")));

                try {
                    HBox hBox = FXMLloader.load();
                    OneFavouriteController oneFavouriteController = FXMLloader.getController();
                    oneFavouriteController.setData(recipes.get(i));
                    recipesLayout.getChildren().add(hBox);

//                    loading.setVisible(false);

                }catch(IOException e){
                    e.printStackTrace();
                }
            }
//        });

        System.out.println(".. wychodze z getOneFav");
    }

    private List<Recipe> recipes(){
        System.out.println(".. whodze do z recipes");
        List<Recipe> listRecipes = new ArrayList<>();

        System.out.println("image url: " + imageUrl);

        for(int i=0; i<name.size(); i++) {
            Recipe recipe = new Recipe();
            recipe.setName(name.get(i));
//            recipe.setIngredient(lsOfIngredientsArrayList.get(i));
            recipe.setIngredient(lsOfIngredients);
            recipe.setImageSoruce(imageUrl.get(i));
            recipe.setUrl(urlsRecipe.get(i));
            recipe.setCalories(calories.get(i).split("\\.")[0]);

            listRecipes.add(recipe);
        }
        System.out.println(".. wychodze z recipes");
        return listRecipes;
    }

    public void getMealsFromDB() throws SQLException {
        System.out.println(".. wchodze do getMeals");
        meals = db.getAll("meals");
        int i=0;

//        totalSize = Integer(meals.getFetchSize());
//        latch = new CountDownLatch(totalSize);

        while (meals.next()){
            System.out.println("test db: " + meals.getString("name"));
//        for(int i=0; i<meals.getFetchSize(); i++){
            name.add(meals.getString("name"));
//            for (int i=0; i<meals.getString("ingredients").length(); i++ ) {
//                lsOfIngredients.add(i++, meals.getString("ingredients").split(",")[i++]);;
                System.out.println("lsOfingredients: " + lsOfIngredients);

//            }
            lsOfIngredients.add(i++, meals.getString("ingredients"));
//            lsOfIngredientsArrayList.add(i++, (ArrayList) meals.getArray("ingredients"));
            imageUrl.add(meals.getString("imageUrl"));
            calories.add(meals.getString("calories"));
            urlsRecipe.add(meals.getString("urlRecipe"));
        }
        System.out.println(".. wychodze z getMeals");
//        getOneFav();
    }

    public void onBackButtonClick(ActionEvent event) throws IOException {
        MainController mainController = new MainController();
        mainController.changeScene("main-view.fxml", event);
    }
}
