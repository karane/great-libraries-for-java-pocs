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
import java.util.*;

public class JsonTable extends AbstractTable implements ScannableTable {

    private final File file;
    private final ObjectMapper mapper = new ObjectMapper();

    private List<String> columns;
    private List<SqlTypeName> types;

    public JsonTable(File file) {
        this.file = file;
    }

    @Override
    public RelDataType getRowType(RelDataTypeFactory typeFactory) {
        try {
            JsonNode root = mapper.readTree(file);
            JsonNode first = root.elements().next();

            columns = new ArrayList<>();
            types = new ArrayList<>();

            RelDataTypeFactory.Builder builder = typeFactory.builder();

            first.fields().forEachRemaining(entry -> {
                String name = entry.getKey();
                JsonNode value = entry.getValue();

                SqlTypeName type;
                if (value.isInt()) type = SqlTypeName.INTEGER;
                else if (value.isLong()) type = SqlTypeName.BIGINT;
                else if (value.isDouble()) type = SqlTypeName.DOUBLE;
                else if (value.isBoolean()) type = SqlTypeName.BOOLEAN;
                else type = SqlTypeName.VARCHAR;

                columns.add(name);
                types.add(type);
                builder.add(name, type);
            });

            return builder.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
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
                    } else if (v.isInt()) {
                        row[i] = v.intValue();
                    } else if (v.isLong()) {
                        row[i] = v.longValue();
                    } else if (v.isDouble()) {
                        row[i] = v.doubleValue();
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
            throw new RuntimeException(e);
        }
    }
}
