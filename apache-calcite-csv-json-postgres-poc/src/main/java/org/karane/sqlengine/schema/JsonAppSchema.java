package org.karane.sqlengine.schema;

import org.apache.calcite.schema.Schema;
import org.apache.calcite.schema.SchemaVersion;
import org.apache.calcite.schema.Table;
import org.apache.calcite.schema.impl.AbstractSchema;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class JsonAppSchema extends AbstractSchema {

    private final File baseDir;

    public JsonAppSchema(File baseDir) {
        this.baseDir = baseDir;
    }

    @Override
    protected Map<String, Table> getTableMap() {
        Map<String, Table> tables = new HashMap<>();

        tables.put(
                "orders",
                new JsonTable(new File(baseDir, "orders.json"))
        );

        return tables;
    }

    /**
     * REQUIRED for Calcite federated queries
     */
    @Override
    public Schema snapshot(SchemaVersion version) {
        return this;
    }
}
