package org.karane.sqlengine.schema;

import org.karane.sqlengine.table.UsersTable;
import org.apache.calcite.schema.Table;
import org.apache.calcite.schema.impl.AbstractSchema;

import java.util.Map;

public class AppSchema extends AbstractSchema {

    @Override
    protected Map<String, Table> getTableMap() {
        return Map.of(
            "USERS", new UsersTable()
        );
    }
}
