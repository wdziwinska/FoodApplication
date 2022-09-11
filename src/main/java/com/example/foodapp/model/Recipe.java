package com.example.foodapp.model;

import java.util.ArrayList;

public class Recipe {

    private String name;
    private String imageSoruce;
    private ArrayList<String> ingredients;
    private String url;
    public String calories;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageSoruce() {
        return imageSoruce;
    }

    public void setImageSoruce(String imageSoruce) {
        this.imageSoruce = imageSoruce;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<String> getIngredient() {
        return ingredients;
    }

    public void setIngredient(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }

}
