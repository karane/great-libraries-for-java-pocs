package org.karane.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonElementAndParserExample {
    public static void main(String[] args) {
        String json = "{ \"brand\": \"Ford\", \"year\": 2020, \"price\": 32000.00 }";

        JsonElement jsonElement = JsonParser.parseString(json);
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        String brand = jsonObject.get("brand").getAsString();
        int year = jsonObject.get("year").getAsInt();
        double price = jsonObject.get("price").getAsDouble();

        System.out.println("Parsed Brand: " + brand);
        System.out.println("Parsed Year: " + year);
        System.out.println("Parsed Price: " + price);
    }
}
