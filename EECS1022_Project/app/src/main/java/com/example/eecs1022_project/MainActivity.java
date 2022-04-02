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
        Model model = new Model();
        intent.putExtra(EXTRA_MESSAGE, model.selectRandom());
        startActivity(intent);
    }
}
