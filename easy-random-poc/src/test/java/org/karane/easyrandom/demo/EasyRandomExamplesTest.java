package org.karane.easyrandom.demo;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.karane.easyrandom.model.Person;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class EasyRandomExamplesTest {
    @Test
    void testRandomPersonNotNull() {
        EasyRandom easyRandom = new EasyRandom();
        Person person = easyRandom.nextObject(Person.class);

        assertNotNull(person);
        assertNotNull(person.getName());
        assertTrue(person.getAge() >= 0); // ages are non-negative
    }

    @Test
    void testReproducibilityWithSeed() {

        EasyRandom r1 = new EasyRandom();
        EasyRandom r2 = new EasyRandom();

        Person p1 = r1.nextObject(Person.class);
        Person p2 = r2.nextObject(Person.class);

        assertEquals(p1.toString(), p2.toString(), "Objects should be identical with same seed");
    }

    @Test
    void testRandomListOfPeople() {
        EasyRandom easyRandom = new EasyRandom();
        var people = easyRandom.objects(Person.class, 3).toList();

        assertEquals(3, people.size());
        people.forEach(p -> assertNotNull(p.getName()));
    }

    @Test
    @SuppressWarnings("unchecked")
    void testRandomMap() {
        EasyRandom easyRandom = new EasyRandom();
        Map<String, Person> map = easyRandom.nextObject(Map.class);

        assertNotNull(map);
        assertFalse(map.isEmpty());
        map.forEach((k, v) -> {
            assertNotNull(k);
            assertNotNull(v);
        });
    }

}