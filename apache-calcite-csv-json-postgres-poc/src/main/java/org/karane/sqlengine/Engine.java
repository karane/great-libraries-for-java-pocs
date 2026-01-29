package org.karane.sqlengine;

import org.apache.calcite.adapter.csv.CsvSchema;
import org.apache.calcite.adapter.csv.CsvTable;
import org.apache.calcite.adapter.jdbc.JdbcSchema;
import org.apache.calcite.jdbc.CalciteConnection;
import org.apache.calcite.schema.SchemaPlus;
import org.karane.sqlengine.schema.JsonAppSchema;
import org.karane.sqlengine.util.ResultSetPrinter;
import org.postgresql.ds.PGSimpleDataSource;

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

        // PostgreSQL DataSource
        PGSimpleDataSource pgDs = new PGSimpleDataSource();
        pgDs.setServerNames(new String[]{"localhost"});
        pgDs.setPortNumbers(new int[]{5432});
        pgDs.setDatabaseName("appdb");
        pgDs.setUser("appuser");
        pgDs.setPassword("apppass");

        // JDBC schema
        root.add(
                "postgres",
                JdbcSchema.create(
                        root,
                        "postgres",
                        pgDs,
                        null,
                        null
                )
        );

        try (
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery("""
                    SELECT
                        u."name",
                        o."product",
                        o."amount",
                        p."method",
                        p."total"
                    FROM "csv"."users" u
                    JOIN "json"."orders" o
                        ON u.id = o.user_id
                    JOIN "postgres"."payments" p
                        ON u.id = p.user_id
                    WHERE o.amount > 50.0
                      AND p.total > 10.0
                    ORDER BY p.total DESC
                """)
        ) {
            ResultSetPrinter.print(rs);
        }

        connection.close();
    }
}
