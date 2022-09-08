package com.example.foodapp.model;

import java.util.List;

public class Recipe {

    private String name;
    private String imageSoruce;
    private List<String> ingredients;

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

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

}
