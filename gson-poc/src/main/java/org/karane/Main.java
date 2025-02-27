package org.karane;

import org.karane.gson.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Running Gson Examples...\n");

        System.out.println("1. Basic Serialization & Deserialization:");
        GsonBasicsExample.main(args);
        System.out.println("\n-------------------------\n");

        System.out.println("2. Custom Serialization & Deserialization:");
        CustomSerializationExample.main(args);
        System.out.println("\n-------------------------\n");

        System.out.println("3. Excluding Fields Using @Expose:");
        ExcludeFieldsExample.main(args);
        System.out.println("\n-------------------------\n");

        System.out.println("4. Parsing JSON Using JsonElement & JsonParser:");
        JsonElementAndParserExample.main(args);
        System.out.println("\n-------------------------\n");

        System.out.println("5. Pretty Printing JSON:");
        PrettyPrintingExample.main(args);
        System.out.println("\n-------------------------\n");

        System.out.println("6. Parsing and Iterating Over JSON Arrays:");
        JsonArrayExample.main(args);
        System.out.println("\n-------------------------\n");

        System.out.println("All examples executed successfully!");
    }
}
