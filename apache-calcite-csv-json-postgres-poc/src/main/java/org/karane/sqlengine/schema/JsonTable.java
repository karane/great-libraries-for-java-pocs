package org.karane.sqlengine.schema;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.calcite.DataContext;
import org.apache.calcite.linq4j.Enumerable;
import org.apache.calcite.linq4j.Linq4j;
import org.apache.calcite.rel.type.RelDataType;
import org.apache.calcite.rel.type.RelDataTypeFactory;
import org.apache.calcite.schema.ScannableTable;
import org.apache.calcite.schema.impl.AbstractTable;
import org.apache.calcite.sql.type.SqlTypeName;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class JsonTable extends AbstractTable implements ScannableTable {

    private final File file;
    private final ObjectMapper mapper = new ObjectMapper();

    private List<String> columns;

    public JsonTable(File file) {
        this.file = file;
    }

    @Override
    public RelDataType getRowType(RelDataTypeFactory typeFactory) {
        try {
            JsonNode root = mapper.readTree(file);
            JsonNode first = root.elements().next();

            columns = new ArrayList<>();
            RelDataTypeFactory.Builder builder = typeFactory.builder();

            first.fields().forEachRemaining(entry -> {
                String name = entry.getKey();
                JsonNode value = entry.getValue();

                RelDataType type;

                if (value.isNumber()) {
                    // Always use DECIMAL for numeric values (join-safe)
                    type = typeFactory.createSqlType(
                            SqlTypeName.DECIMAL,
                            18, 2
                    );
                } else if (value.isBoolean()) {
                    type = typeFactory.createSqlType(SqlTypeName.BOOLEAN);
                } else {
                    type = typeFactory.createSqlType(SqlTypeName.VARCHAR, 255);
                }

                columns.add(name);
                builder.add(name, type);
            });

            return builder.build();
        } catch (Exception e) {
            throw new RuntimeException("Failed to infer JSON schema", e);
        }
    }

    @Override
    public Enumerable<Object[]> scan(DataContext root) {
        try {
            JsonNode rootNode = mapper.readTree(file);
            List<Object[]> rows = new ArrayList<>();

            for (JsonNode node : rootNode) {
                Object[] row = new Object[columns.size()];

                for (int i = 0; i < columns.size(); i++) {
                    JsonNode v = node.get(columns.get(i));

                    if (v == null || v.isNull()) {
                        row[i] = null;
                    } else if (v.isNumber()) {
                        // Must match DECIMAL → BigDecimal
                        row[i] = v.decimalValue();
                    } else if (v.isBoolean()) {
                        row[i] = v.booleanValue();
                    } else {
                        row[i] = v.asText();
                    }
                }

                rows.add(row);
            }

            return Linq4j.asEnumerable(rows);
        } catch (Exception e) {
            throw new RuntimeException("Failed to scan JSON table", e);
        }
    }
}
