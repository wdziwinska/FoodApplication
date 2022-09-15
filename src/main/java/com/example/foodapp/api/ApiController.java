package com.example.foodapp.api;

import com.google.gson.JsonObject;

public class ApiController {

    public static Api api;

    public ApiController(Api api) {
        this.api = api;
    }

    public static JsonObject get(String path, String query) {
        JsonObject jsonObject = new JsonObject();

        try {
            jsonObject = api.requestBuilder(path, query.replace(" ", "%20"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public JsonObject getFromURL(String url) {
        JsonObject jsonObject = new JsonObject();
        try {
            jsonObject = api.fromURLRequest(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
