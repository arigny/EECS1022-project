package com.example.eecs1022_project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class Model {

    // Initialize list of valid animals in the database
    private ArrayList<String> allAnimals = new ArrayList<>(Arrays.asList("dog", "cat", "mouse", "cow", "duck", "tiger", "monkey", "kimodo dragon", "penguin", "fish"));

    public void Model () {}

    // Select a random animal in the database
    public String selectRandom(){
        int random = (int) Math.floor(Math.random()*(9 - 0 + 1) + 0);
        String randomSelected = (String) this.allAnimals.get(random);
        return randomSelected;
    }

    // Validate that the input animal is a valid animal in the database
    public boolean validateAnimal (String animal) {
        if (this.allAnimals.contains(animal.toLowerCase(Locale.ROOT))) {
            return true;
        } else return false;
    }
}
