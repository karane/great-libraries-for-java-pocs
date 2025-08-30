package org.karane.easyrandom.demo;


import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.karane.easyrandom.model.Person;

import static org.junit.jupiter.api.Assertions.*;

public class FuzzyPersonTest {

    private final EasyRandom easyRandom = new EasyRandom();

    @RepeatedTest(50)
    void testPersonNameIsNotNullOrEmpty() {
        Person person = easyRandom.nextObject(Person.class);

        assertNotNull(person.getName(), "Name should not be null");
        assertFalse(person.getName().isBlank(), "Name should not be blank");
    }

    @RepeatedTest(50)
    void testPersonAgeIsWithinExpectedRange() {
        Person person = easyRandom.nextObject(Person.class);

        assertTrue(person.getAge() >= 0, "Age should be a valid age");
    }

    @RepeatedTest(50)
    void testPersonAddressAlwaysInitialized() {
        Person person = easyRandom.nextObject(Person.class);

        assertNotNull(person.getAddress(), "Address should not be null");
        assertNotNull(person.getAddress().getStreet(), "Street should not be null");
        assertNotNull(person.getAddress().getCity(), "City should not be null");
    }

    @Test
    void fuzzingWithCustomLimitations() {
        EasyRandom limitedRandom = new EasyRandom();

        for (int i = 0; i < 100; i++) {
            Person person = limitedRandom.nextObject(Person.class);

            assertNotNull(person.getName());
            assertTrue(person.getName().length() < 50, "Name too long?");


            assertTrue(person.getAge() >= 0 && person.getAge() < 200, "Age is out of expected fuzzy range");
        }
    }
}
