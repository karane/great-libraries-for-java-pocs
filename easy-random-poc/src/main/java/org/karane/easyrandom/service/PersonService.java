package org.karane.easyrandom.service;

import org.karane.easyrandom.model.Person;

public class PersonService {

    public boolean isAdult(Person person) {
        if (person == null) throw new IllegalArgumentException("Person cannot be null");
        return person.getAge() >= 18;
    }

    public String displayName(Person person) {
        if (person == null) return "Unknown";
        String name = person.getName();
        if (name == null || name.isBlank()) {
            return "Anonymous";
        }
        return name.trim();
    }

    public String lifeStage(Person person) {
        if (person == null) return "Unknown";
        int age = person.getAge();
        if (age < 0) return "Invalid";
        if (age < 13) return "Child";
        if (age < 20) return "Teen";
        if (age < 65) return "Adult";
        return "Senior";
    }
}
