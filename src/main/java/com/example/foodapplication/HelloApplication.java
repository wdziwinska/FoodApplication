package com.example.foodapplication;

import com.example.foodapplication.api.Api;
import com.example.foodapplication.api.ApiController;
import com.google.gson.JsonObject;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {

        Api api = new Api();
        ApiController apiController = new ApiController(api);
        String app_id = "6030db9a";
        String app_key = "facae2fab72ad57305feb9521499cb04";
        String q = "chicken";
        String diet = "high-protein";
        String health = "pork-free";
        String cuisineType = "French";
        String mealType = "Dinner";
        String dishType = "Main%20course";

        JsonObject jsonObject = ApiController.get("recipes", "v2?type=public&q=" + q + "&app_id=" + app_id +"&app_key=" + app_key + "&diet=" + diet
                + "&health=" + health + "&cuisineType=" + cuisineType + "&mealType=" + mealType + "&dishType=" + dishType);
        System.out.println(jsonObject);
        launch();




//        try{
//            URL url = new URL("https://api.edamam.com/api/recipes/v2");
//
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod("GET");
//            connection.connect();
//
//            //if connect is made
//            int responseCode = connection.getResponseCode();
//
//            //200 ok
//            if(responseCode != 200){
//                throw new RuntimeException("HttpResponseCode: " + responseCode);
//            }
//            else {
//                StringBuilder informationString = new StringBuilder();
//                Scanner scanner = new Scanner(url.openStream());
//
//                while (scanner.hasNext()){
//                    informationString.append(scanner.nextLine());
//                }
//
//                scanner.close();
//
//                System.out.println(informationString);
//
//                //library Setup with Maven is used to convert strings to JSON
//                JSONParser parse = new JSONParser();
//
//            }
//
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}