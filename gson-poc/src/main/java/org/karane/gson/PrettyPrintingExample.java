package org.karane.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.karane.gson.model.Car;

public class PrettyPrintingExample {
    public static void main(String[] args) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Car car = new Car("Audi", 2019, 39000.99);
        String prettyJson = gson.toJson(car);

        System.out.println("Pretty Printed JSON:\n" + prettyJson);
    }
}
