package com.example.eecs1022_project;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GetPhoto {

    final OkHttpClient client = new OkHttpClient();

    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url("https://bing-image-search1.p.rapidapi.com/images/search?q=giraffe&count=10")
                .get()
                .addHeader("X-RapidAPI-Host", "bing-image-search1.p.rapidapi.com")
                .addHeader("X-RapidAPI-Key", "c052815b7cmsh147118f3cfaf92ep1f0328jsn5e3f2b19757e")
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public static void main(String[] args) throws IOException {
        GetPhoto photo = new GetPhoto();
        String response = photo.run("https://raw.github.com/square/okhttp/master/README.md");
        System.out.println(response);
    }
}
