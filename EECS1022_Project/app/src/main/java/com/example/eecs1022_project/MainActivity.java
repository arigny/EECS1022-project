package com.example.eecs1022_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // On SUBMIT click, send query to DisplayAnimal activity
    public void submitAnimal(View view) {
        Intent intent = new Intent(this, DisplayAnimal.class);
        EditText editText = (EditText) findViewById(R.id.input);
        // Get message from EditText box and send to next activity
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    // On RANDOM ANIMAL, send random animal query to displayRandomAnimal activity
    public void submitRandom(View view) {
        Intent intent = new Intent(this, displayRandomAnimal.class);
        intent.putExtra(EXTRA_MESSAGE, selectRandom());
        startActivity(intent);
    }

    public String selectRandom(){
        // When random animal button is clicked, use random int between 0 and 9 to select random animal from the list
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

        int random = (int) Math.floor(Math.random()*(9 - 0 + 1) + 0);
        String randomSelected = (String) allAnimals.get(random);

        return randomSelected;
    }

}
