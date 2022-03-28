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
import java.io.InputStream;

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

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.textView);
        textView.setText(message);

        // Select proper image to display
        String animal;
        if (selectedAnimal.equalsIgnoreCase("dog")) {
            animal = "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/dog-puppy-on-garden-royalty-free-image-1586966191.jpg?crop=0.752xw:1.00xh;0.175xw,0&resize=640:*";
        }
        else if (selectedAnimal.equalsIgnoreCase("cat")) {
            animal = "https://i2-prod.mirror.co.uk/incoming/article25609246.ece/ALTERNATES/s1200c/0_PUSS-IN-BOOTS.jpg";
        }
        else if (selectedAnimal.equalsIgnoreCase("mouse")) {
            animal = "https://media.baamboozle.com/uploads/images/44143/1610710817_77416";
        }
        else if (selectedAnimal.equalsIgnoreCase("quokka")) {
            animal = "https://imgk.timesnownews.com/74666103_2454188944828540_4366738148824794550_n_1574090431__rend_1_1.jpg?tr=w-360";
        }
        else {
            animal = "https://www.prestashop.com/sites/default/files/wysiwyg/404_not_found.png";
        }

        // Download image for view
        new DownloadImageTask((ImageView) findViewById(R.id.imageView))
                .execute(animal);
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