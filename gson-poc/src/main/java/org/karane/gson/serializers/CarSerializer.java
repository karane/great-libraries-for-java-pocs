package org.karane.gson.serializers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import org.karane.gson.model.Car;

import java.lang.reflect.Type;

public class CarSerializer implements JsonSerializer<Car> {
    @Override
    public JsonElement serialize(Car car, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("brand_name", car.getBrand());
        jsonObject.addProperty("manufactured_year", car.getYear());
        jsonObject.addProperty("price_usd", car.getPrice());
        return jsonObject;
    }
}
