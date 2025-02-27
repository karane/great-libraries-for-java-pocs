package org.karane.gson.serializers;

import com.google.gson.*;
import org.karane.gson.model.Car;

import java.lang.reflect.Type;

public class CarDeserializer implements JsonDeserializer<Car> {
    @Override
    public Car deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String brand = jsonObject.get("brand_name").getAsString();
        int year = jsonObject.get("manufactured_year").getAsInt();
        double price = jsonObject.get("price_usd").getAsDouble();
        return new Car(brand, year, price);
    }
}
