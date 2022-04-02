package com.example.eecs1022_project;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ModelTest {

    @Test
    // Test that selectRandom() works and returns a valid animal
    public void selectRandom() {
        Model random = new Model();
        String selectedRandom = random.selectRandom();
        boolean actual = random.validateAnimal(selectedRandom);
        assertTrue(actual);
    }

    @Test
    // Test that validateAnimal() returns true for lowercase valid animal
    public void validateAnimal_01() {
        Model validate = new Model();
        String dog = "dog";
        assertTrue(validate.validateAnimal(dog));
    }

    @Test
    // Test that validateAnimal() returns true for uppercase valid animal
    public void validateAnimal_02() {
        Model validate = new Model();
        String dog = "DOG";
        assertTrue(validate.validateAnimal(dog));
    }

    @Test
    // Test that validateAnimal() returns false for invalid animal
    public void invalidAnimal() {
        Model validate = new Model();
        String frog = "frog";
        assertFalse(validate.validateAnimal(frog));
    }
}
