package org.karane.gson.model;

import com.google.gson.annotations.Expose;

public class SecureCar {
    @Expose
    String brand;

    @Expose
    int year;

    transient double price; // Will be excluded

    public SecureCar(String brand, int year, double price) {
        this.brand = brand;
        this.year = year;
        this.price = price;
    }
}
