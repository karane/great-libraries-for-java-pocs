package org.karane.easyrandom.model;

public class Address {
    private String street;
    private String city;

    // getters & setters
    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    @Override
    public String toString() {
        return "%s, %s".formatted(street, city);
    }
}
