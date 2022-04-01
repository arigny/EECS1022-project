package com.example.eecs1022_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitAnimal(View view) {
        Intent intent = new Intent(this, DisplayAnimal.class);
        EditText editText = (EditText) findViewById(R.id.input);
        // Get message from EditText box and send to next activity
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void submitRandom(View view) {
        Intent intent = new Intent(this, DisplayAnimal.class);
        EditText editText = (EditText) findViewById(R.id.input);
        // Random button randomly assigns an animal
        int random = (int) Math.floor(Math.random()*(9 - 0 + 1) + 0);
//        intent.putExtra(EXTRA_MESSAGE, random);
//        startActivity(intent);
        System.out.println("Random number: "+ random);

        // Logic to go in displayRandomAnimal activity
        ArrayList allAnimals = new ArrayList<String>();
        allAnimals.add("dog");
        allAnimals.add("cat");
        allAnimals.add("mouse");
        allAnimals.add("cow");
        allAnimals.add("duck");
        allAnimals.add("tiger");
        allAnimals.add("monkey");
        allAnimals.add("kimodo dragon");
        allAnimals.add("penguin");
        allAnimals.add("fish");

        System.out.println(allAnimals.get(random));
    }

}