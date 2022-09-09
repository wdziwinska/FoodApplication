package com.example.foodapp;

import com.example.foodapp.api.Api;
import com.example.foodapp.api.ApiController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        stage.setTitle("CookBook!");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {

        Api api = new Api();
        ApiController apiController = new ApiController(api);
       // MealsController mealsController = new MealsController(api, apiController);
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