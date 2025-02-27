package org.karane.gson;

import com.google.gson.Gson;
import org.karane.gson.model.Car;

public class GsonBasicsExample {
    public static void main(String[] args) {
        Gson gson = new Gson();

        // Serialization
        Car car = new Car("Tesla", 2022, 55000.99);
        String json = gson.toJson(car);
        System.out.println("Serialized: " + json);

        // Deserialization
        Car deserialized = gson.fromJson(json, Car.class);
        System.out.println("Deserialized: " + deserialized.getBrand() + ", " + deserialized.getYear() + ", " + deserialized.getPrice());
    }
}
