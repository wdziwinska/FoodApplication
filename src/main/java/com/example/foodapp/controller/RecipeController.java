package com.example.foodapp.controller;

import com.example.foodapp.Main;
import com.example.foodapp.model.Recipe;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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

    public ArrayList<String> name;
    public ArrayList<String> imageUrl;
    public ArrayList<ArrayList> lsOfIngredients;


    public void getElementsToRecipe(ArrayList<String> labelTitle, ArrayList<ArrayList> ingredient, ArrayList<String> url) {

        name = labelTitle;
        imageUrl = url;
        lsOfIngredients = ingredient;

        for(int i=0; i<name.size(); i++) {
            label.setText(labelTitle.get(i));
            ingredientLines.setText(String.valueOf(ingredient));
            listOfIngredients.getItems().addAll(ingredient.get(i));
            imageView.setImage(new Image(url.get(i)));
        }

        getOneRecipe();
    }

    public void onButtonTestClick() {
        System.out.println("HelloWorld");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        List<Recipe> recipes = new ArrayList<>(recipes());
//
//        Platform.runLater(() ->{
//            for (int i = 0; i<recipes.size(); i++){
////                FXMLLoader FXMLloader = new FXMLLoader();
////                FXMLloader.setLocation(getClass().getResource("oneRecipe-view.fxml"));
//                FXMLLoader FXMLloader = new FXMLLoader(Main.class.getResource("oneRecipe-view.fxml"));
//
//                try {
//                    HBox hBox = FXMLloader.load();
//                    OneRecipeViewController oneRecipeViewController = FXMLloader.getController();
//                    oneRecipeViewController.setData(recipes.get(i));
//                    recipesLayout.getChildren().add(hBox);
//
//
//                }catch(IOException e){
//                    e.printStackTrace();
//                }
//            }
//        });
    }

    public void getOneRecipe(){
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
            listRecipes.add(recipe);
        }

//        recipe.setName("Eggs withtomatoes - test2");
//        recipe.setIngredients(Arrays.asList("Ing: 2 egg", "1 glass of water", "5 tomatoes"));
//        recipe.setImageSoruce("https://edamam-product-images.s3.amazonaws.com/web-img/fc3/fc3f30cda28a700d007578691e1c6992.jpg?X-Amz-Security-Token=IQoJb3JpZ2luX2VjEJj%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FwEaCXVzLWVhc3QtMSJIMEYCIQC0RuID%2BnRAn40AW5wQZzPu%2BZLmr1egsEQCBd5ytDXezQIhANN%2BI1UJpc7qWak0ydjuV7QZIvsaumG8bG4bthGk%2FSRxKswECDEQABoMMTg3MDE3MTUwOTg2Igwylpcl10Hk2%2BIPMo0qqQQsTNnKtdxdlFRgsQzHh5TKycj%2Bot8dlAYF64os7MFrluehPCDwUQWAlf%2BVIFm2jxa%2B%2BB8ol5Lg98KbMuFP%2B97a5HrP0FSkQU8i83UJo%2BkvlLloNhy4QNojvxG34neizvqbL71ZH8%2Fq57910WLPC2NRbOyusLisyISedzFB0V3G0WQ4Kfp2i3HPvGUIJHOw4yUJQEG1gMlfRYXpxy%2FntQUJsMTmfzUN0gEv1FSjrv%2FyI4qqvsWqQKp1sl3hS9jOJyyxCMRGy42pJIxkJFewZhuAq%2BzaKDCKDaSQ1QBynCcHaW%2F63B5j38rNjKItPDIWiE77A0qvtTjGcidyQf708dNpVUe%2FfSiyryozMdxFzjfwIlDmv1rc%2F%2FxwCT7p4878KtomD3e0%2BQZySbGP7mSdQtyFGFQcEomz9CfHlJ03AKz1lqyP1HLcpefaMZWO%2FFsSpy%2BphkD61jyB%2FpjYNpcEiliN%2FpETDvTFluNV3ddAvLpxZ3vJ18kTdnZQFpQn76y8UNo3l%2Fup4b9qWzDS8DS9iRpOpn8o4GyWjztvQKBWmcyoMJ9%2Fe0MuvxqzP%2BLooPEtj2s%2FSBzVpD%2FxUBhxVGsDtWy12xkfjGF6xzKMeFUzQNOLQFiWfvgXNi5Lyik1OKQ97%2FwFc%2BKWYc7x%2Flc1vUKducSlXYpeFKCL9KOjrW76xFQzPtiRNIzlPcBEVktuDyn6IFImbA72O9hI1x3mNwajQ2Tutsyv0SwHZ57tMJaV6JgGOqgBP%2FW50Aalo87W8obIT0vhRwSF13qPmwZuaZNGilybU7amLH2E8HjsuCfhOwkGMqH6xu4JcX3EOXSQYShs3UH4tMUxHwwW3mBlvQKxiER1EDA7i6OesYp48jYmdPWmOy%2Bq6qX%2F%2F0WcEmUV1u8%2Bilx3BdB%2FjejzE3%2BfOEnS7qabUFjgVDEHG8pCFkkmjL39LginRArWSNLuAjyrZtqfoGwlQY9vCc%2FCIIZy&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20220908T165255Z&X-Amz-SignedHeaders=host&X-Amz-Expires=3600&X-Amz-Credential=ASIASXCYXIIFIMRIHSGQ%2F20220908%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Signature=fa2ec03d34b6f008a4c8f323dcdb04833b5c92740680bfdb162ce9cf71135448");
//        listRecipes.add(recipe);
//
//        recipe.setName("Eggs withtomatoes - test3");
//        recipe.setIngredients(Arrays.asList("Ing: 1,5 egg", "2 glass of water", "3 tomatoes"));
//        recipe.setImageSoruce("https://edamam-product-images.s3.amazonaws.com/web-img/fc3/fc3f30cda28a700d007578691e1c6992.jpg?X-Amz-Security-Token=IQoJb3JpZ2luX2VjEJj%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FwEaCXVzLWVhc3QtMSJIMEYCIQC0RuID%2BnRAn40AW5wQZzPu%2BZLmr1egsEQCBd5ytDXezQIhANN%2BI1UJpc7qWak0ydjuV7QZIvsaumG8bG4bthGk%2FSRxKswECDEQABoMMTg3MDE3MTUwOTg2Igwylpcl10Hk2%2BIPMo0qqQQsTNnKtdxdlFRgsQzHh5TKycj%2Bot8dlAYF64os7MFrluehPCDwUQWAlf%2BVIFm2jxa%2B%2BB8ol5Lg98KbMuFP%2B97a5HrP0FSkQU8i83UJo%2BkvlLloNhy4QNojvxG34neizvqbL71ZH8%2Fq57910WLPC2NRbOyusLisyISedzFB0V3G0WQ4Kfp2i3HPvGUIJHOw4yUJQEG1gMlfRYXpxy%2FntQUJsMTmfzUN0gEv1FSjrv%2FyI4qqvsWqQKp1sl3hS9jOJyyxCMRGy42pJIxkJFewZhuAq%2BzaKDCKDaSQ1QBynCcHaW%2F63B5j38rNjKItPDIWiE77A0qvtTjGcidyQf708dNpVUe%2FfSiyryozMdxFzjfwIlDmv1rc%2F%2FxwCT7p4878KtomD3e0%2BQZySbGP7mSdQtyFGFQcEomz9CfHlJ03AKz1lqyP1HLcpefaMZWO%2FFsSpy%2BphkD61jyB%2FpjYNpcEiliN%2FpETDvTFluNV3ddAvLpxZ3vJ18kTdnZQFpQn76y8UNo3l%2Fup4b9qWzDS8DS9iRpOpn8o4GyWjztvQKBWmcyoMJ9%2Fe0MuvxqzP%2BLooPEtj2s%2FSBzVpD%2FxUBhxVGsDtWy12xkfjGF6xzKMeFUzQNOLQFiWfvgXNi5Lyik1OKQ97%2FwFc%2BKWYc7x%2Flc1vUKducSlXYpeFKCL9KOjrW76xFQzPtiRNIzlPcBEVktuDyn6IFImbA72O9hI1x3mNwajQ2Tutsyv0SwHZ57tMJaV6JgGOqgBP%2FW50Aalo87W8obIT0vhRwSF13qPmwZuaZNGilybU7amLH2E8HjsuCfhOwkGMqH6xu4JcX3EOXSQYShs3UH4tMUxHwwW3mBlvQKxiER1EDA7i6OesYp48jYmdPWmOy%2Bq6qX%2F%2F0WcEmUV1u8%2Bilx3BdB%2FjejzE3%2BfOEnS7qabUFjgVDEHG8pCFkkmjL39LginRArWSNLuAjyrZtqfoGwlQY9vCc%2FCIIZy&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20220908T165255Z&X-Amz-SignedHeaders=host&X-Amz-Expires=3600&X-Amz-Credential=ASIASXCYXIIFIMRIHSGQ%2F20220908%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Signature=fa2ec03d34b6f008a4c8f323dcdb04833b5c92740680bfdb162ce9cf71135448");
//        listRecipes.add(recipe);

        return listRecipes;
    }
}