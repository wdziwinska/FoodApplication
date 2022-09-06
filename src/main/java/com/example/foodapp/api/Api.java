package com.example.foodapp.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Api {

    public JsonObject requestBuilder(String path, String query) throws Exception {
        HttpGet httpget;
//        if (query == null) {
            httpget = new HttpGet("https://api.edamam.com/api/recipes/" + path + query);
        System.out.println("hhtpGet: " + httpget);
//        }
//        else {
//            httpget = new HttpGet("https://api.edamam.com/api/recipes/v2?type=public" + qValue + "/" + query);
//        }
        return getRequest(httpget);
    }

    public JsonObject getRequest(HttpGet getRequest) throws Exception{

        HttpClient httpClient = HttpClientBuilder.create().build();
        getRequest.addHeader("accept", "application/json");
        HttpResponse response = httpClient.execute(getRequest);
        if (response.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException("Error : " + response.getStatusLine().getStatusCode());
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

        String line;
        StringBuilder stringBuilder = new StringBuilder();
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }
        JsonObject jsonObject = new Gson().fromJson(stringBuilder.toString(),JsonObject.class);
        bufferedReader.close();

        return jsonObject;
    }
    public JsonObject fromURLRequest(String url) throws Exception {
        HttpGet httpget = new HttpGet(url);
        return getRequest(httpget);
    }
}
