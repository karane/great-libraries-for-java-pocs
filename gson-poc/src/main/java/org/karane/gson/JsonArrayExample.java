package org.karane.gson;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonArrayExample {
    public static void main(String[] args) {
        String jsonArrayString = "[{\"brand\": \"Honda\", \"year\": 2018, \"price\": 22000.00}, " +
                                  "{\"brand\": \"Toyota\", \"year\": 2021, \"price\": 28000.00}]";

        JsonElement jsonElement = JsonParser.parseString(jsonArrayString);
        JsonArray jsonArray = jsonElement.getAsJsonArray();

        System.out.println("Iterating over JSON array:");
        for (JsonElement element : jsonArray) {
            JsonObject obj = element.getAsJsonObject();
            System.out.println(obj.get("brand").getAsString() + " - " + obj.get("year").getAsInt() + " - $" + obj.get("price").getAsDouble());
        }
    }
}
