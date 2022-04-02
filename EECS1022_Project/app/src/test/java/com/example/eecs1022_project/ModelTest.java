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
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void selectRandom() {
        Model random = new Model();
        String selectedRandom = random.selectRandom();
        boolean actual = random.validateAnimal(selectedRandom);
        assertTrue(actual);
    }

    @Test
    public void validateAnimal() {
        Model validate = new Model();
        String dog = "dog";
        assertTrue(validate.validateAnimal(dog));
    }

    @Test
    public void invalidAnimal() {
        Model validate = new Model();
        String frog = "frog";
        assertFalse(validate.validateAnimal(frog));
    }
}