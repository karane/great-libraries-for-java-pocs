package org.karane.easyrandom.demo;


import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.karane.easyrandom.model.Person;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class EasyRandomExamples {

    public static void basicExample() {
        EasyRandom easyRandom = new EasyRandom();
        Person randomPerson = easyRandom.nextObject(Person.class);
        System.out.println("Basic random person: " + randomPerson);
    }

    public static void customizedExample() {
        EasyRandomParameters parameters = new EasyRandomParameters()
                .seed(123L) // reproducibility
                .stringLengthRange(5, 10)
                .collectionSizeRange(1, 2)
                .randomize(Integer.class, () -> 18 + new Random().nextInt(50));

        EasyRandom easyRandom = new EasyRandom(parameters);
        Person customPerson = easyRandom.nextObject(Person.class);
        System.out.println("Customized random person: " + customPerson);
    }

    public static void listExample() {
        EasyRandom easyRandom = new EasyRandom();
        List<Person> people = easyRandom.objects(Person.class, 5).toList();
        System.out.println("\nList of random people:");
        people.forEach(System.out::println);
    }

    public static void mapExample() {
        EasyRandom easyRandom = new EasyRandom();
        Map<String, Person> directory = easyRandom.nextObject(Map.class);

        System.out.println("\nRandom Map (String → Person):");
        directory.forEach((key, value) -> 
            System.out.println("Key: " + key + ", Value: " + value));
    }
}
