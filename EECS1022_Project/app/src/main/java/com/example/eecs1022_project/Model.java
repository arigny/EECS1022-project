package com.example.eecs1022_project;

import java.util.ArrayList;
import java.util.Arrays;

public class Model {

    private ArrayList<String> allAnimals = new ArrayList<>(Arrays.asList("dog", "cat", "mouse", "cow", "duck", "tiger", "monkey", "kimodo dragon", "penguin", "fish"));

    public void Model () {}

    public String selectRandom(){
        int random = (int) Math.floor(Math.random()*(9 - 0 + 1) + 0);
        String randomSelected = (String) this.allAnimals.get(random);
        return randomSelected;
    }

    public boolean validateAnimal (String animal) {
        if (this.allAnimals.contains(animal)) {
            return true;
        } else return false;
    }
}
