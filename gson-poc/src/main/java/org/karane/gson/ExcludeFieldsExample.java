package org.karane.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.karane.gson.model.SecureCar;

public class ExcludeFieldsExample {
    public static void main(String[] args) {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

        SecureCar car = new SecureCar("Mercedes", 2021, 60000.50);
        String json = gson.toJson(car);
        System.out.println("Serialized (excluding price): " + json);
    }
}
