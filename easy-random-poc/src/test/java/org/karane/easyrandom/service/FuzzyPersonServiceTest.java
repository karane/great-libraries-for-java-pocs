package org.karane.easyrandom.service;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.karane.easyrandom.model.Person;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class FuzzyPersonServiceTest {

    private EasyRandom easyRandom;
    private PersonService personService;

    @BeforeEach
    void setUp() {
        EasyRandomParameters params = new EasyRandomParameters()
                .seed(123L) // reproducibility
                .stringLengthRange(1, 25) // names 1–20 chars
                .collectionSizeRange(1, 30) // lists/maps size if used
                .randomize(Integer.class, () -> {
                    // ages between -5 and 120
                    return -5 + new Random().nextInt(126);
                });

        easyRandom = new EasyRandom(params);
        personService = new PersonService();
    }

    @RepeatedTest(50)
    void testIsAdultNeverThrows() {
        Person person = easyRandom.nextObject(Person.class);

        assertDoesNotThrow(() -> personService.isAdult(person));
    }

    @RepeatedTest(50)
    void testDisplayNameAlwaysNonNullAndShortEnough() {
        Person person = easyRandom.nextObject(Person.class);

        String result = personService.displayName(person);

        assertNotNull(result, "Display name should never be null");
        assertFalse(result.isBlank(), "Display name should not be blank");
        assertTrue(result.length() <= 20, "Display name should be <= 20 characters");
    }

    @RepeatedTest(50)
    void testLifeStageCategoriesAreValid() {
        Person person = easyRandom.nextObject(Person.class);

        String stage = personService.lifeStage(person);

        assertTrue(
                stage.equals("Child") ||
                        stage.equals("Teen") ||
                        stage.equals("Adult") ||
                        stage.equals("Senior") ||
                        stage.equals("Invalid") ||
                        stage.equals("Unknown"),
                "Unexpected life stage: " + stage
        );
    }
}
