package org.karane.sqlengine;

import org.karane.sqlengine.schema.AppSchema;
import org.apache.calcite.jdbc.CalciteConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Engine {

    public static void main(String[] args) throws Exception {

        Connection connection =
                DriverManager.getConnection("jdbc:calcite:");

        CalciteConnection calcite =
                connection.unwrap(CalciteConnection.class);

        calcite.getRootSchema().add("app", new AppSchema());
        calcite.setSchema("app");

        Statement stmt = connection.createStatement();

        ResultSet rs = stmt.executeQuery("""
            SELECT name, age
            FROM users
            WHERE age >= 30
            ORDER BY age
        """);

        while (rs.next()) {
            System.out.println(
                    rs.getString("name") + " " + rs.getInt("age")
            );
        }
    }
}
