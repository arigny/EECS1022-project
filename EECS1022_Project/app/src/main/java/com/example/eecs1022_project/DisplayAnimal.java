package com.example.eecs1022_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DisplayAnimal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_animal);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = "You chose a ";
        String selectedAnimal = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        message += selectedAnimal;

        // Capture the layout's queryView and set the string as its text
        TextView queryView = findViewById(R.id.queryView);
        queryView.setText(message);

        // Capture the layout's hereView and set the string as its text
        TextView hereView = findViewById(R.id.hereView);
        hereView.setText("Here it is:");

        // Check if query animal is in allAnimals. If so, query, download and display it.
        Model model = new Model();

        // If the input is a valid animal, continue
        if (model.validateAnimal(selectedAnimal)) {

            // Search bing image search via RapidAPI url, using OkHttp
            OkHttpClient client = new OkHttpClient();
            String url = "https://bing-image-search1.p.rapidapi.com/images/search?q=" + selectedAnimal + "%20animal&count=10";

            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .addHeader("X-RapidAPI-Host", "bing-image-search1.p.rapidapi.com")
                    .addHeader("X-RapidAPI-Key", "c052815b7cmsh147118f3cfaf92ep1f0328jsn5e3f2b19757e")
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, final Response response) throws IOException {

                    final String responseData = response.body().string();

                    DisplayAnimal.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                JSONObject json = new JSONObject(responseData);
                                JSONArray values = json.getJSONArray("value");
                                //Gets the first search result
                                JSONObject value = values.getJSONObject(0);
                                String imgURL = value.getString("thumbnailUrl");
                                new DownloadImageTask((ImageView) findViewById(R.id.imageView))
                                        .execute(imgURL);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            });

        }

        // If input is not valid, display invalid query messages
        else {
            queryView.setText("Invalid query: " + selectedAnimal);
            hereView.setText("Please select an animal from the list");
        }
    }

    // Download the image from the URL and display it
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        // Set ImageView
        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        // Download the image in the background
        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        // Set the bitmap to the image
        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

}
