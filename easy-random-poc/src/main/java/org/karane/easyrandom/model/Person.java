package org.karane.easyrandom.model;

public class Person {
    private String name;
    private int age;
    private Address address;

    // getters & setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }

    @Override
    public String toString() {
        return "Person{name='%s', age=%d, address=%s}".formatted(name, age, address);
    }
}
