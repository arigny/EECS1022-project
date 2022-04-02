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

public class displayRandomAnimal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_random_animal);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String selectedAnimal = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // Capture the layout's TextView and set the string as its text
        TextView queryView = findViewById(R.id.queryViewRandom);
        queryView.setText("You chose random!");

        // Capture the layout's TextView2 and set the string as its text
        TextView hereView = findViewById(R.id.hereViewRandom);
        String message = "You got a " + selectedAnimal;
        hereView.setText(message);

        // Query, download and display the randomAnimal
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
                // ... check for failure using `isSuccessful` before proceeding

                // Read data on the worker thread
                final String responseData = response.body().string();

                // Run view-related code back on the main thread
                displayRandomAnimal.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject json = new JSONObject(responseData);
                            JSONArray values = json.getJSONArray("value");
                            //Gets the first search result
                            JSONObject value = values.getJSONObject(0);
                            String imgURL = value.getString("thumbnailUrl");
                            new displayRandomAnimal.DownloadImageTask((ImageView) findViewById(R.id.imageViewRandom))
                                    .execute(imgURL);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });

    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

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

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

}
