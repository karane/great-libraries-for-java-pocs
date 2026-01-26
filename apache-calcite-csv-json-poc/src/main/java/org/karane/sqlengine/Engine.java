package org.karane.sqlengine;

import org.apache.calcite.adapter.csv.CsvSchema;
import org.apache.calcite.adapter.csv.CsvTable;
import org.apache.calcite.jdbc.CalciteConnection;
import org.apache.calcite.schema.SchemaPlus;
import org.karane.sqlengine.schema.JsonAppSchema;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class Engine {

    public static void main(String[] args) throws Exception {

        Properties props = new Properties();
        props.setProperty("caseSensitive", "false");

        Connection connection =
                DriverManager.getConnection("jdbc:calcite:", props);

        CalciteConnection calcite =
                connection.unwrap(CalciteConnection.class);

        SchemaPlus root = calcite.getRootSchema();

        // CSV schema
        root.add(
                "csv",
                new CsvSchema(
                        new File("data/csv"),
                        CsvTable.Flavor.SCANNABLE
                )
        );

        // JSON schema
        root.add(
                "json",
                new JsonAppSchema(new File("data/json"))
        );

        Statement stmt = connection.createStatement();

        ResultSet rs = stmt.executeQuery("""
            SELECT
                u."name",
                o."product",
                o."amount"
            FROM "csv"."users" u
            JOIN "json"."orders" o
                ON u.id = o.user_id
            WHERE o.amount > 100
            ORDER BY o.amount
        """);

        while (rs.next()) {

            System.out.println(
                    rs.getString("name") + " | " +
                            rs.getString("product") + " | " +
                            rs.getObject("amount")
            );
        }

        rs.close();
        stmt.close();
        connection.close();
    }
}
