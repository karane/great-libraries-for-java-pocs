package org.karane.gson;

import com.google.gson.*;
import org.karane.gson.model.Car;
import org.karane.gson.serializers.CarDeserializer;
import org.karane.gson.serializers.CarSerializer;

public class CustomSerializationExample {
    public static void main(String[] args) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Car.class, new CarSerializer())
                .registerTypeAdapter(Car.class, new CarDeserializer())
                .create();

        Car car = new Car("BMW", 2023, 45000.75);
        String json = gson.toJson(car);
        System.out.println("Custom Serialized: " + json);

        Car deserializedCar = gson.fromJson(json, Car.class);
        System.out.println("Custom Deserialized: " + deserializedCar.getBrand() + ", " + deserializedCar.getYear() + ", " + deserializedCar.getPrice());
    }
}
