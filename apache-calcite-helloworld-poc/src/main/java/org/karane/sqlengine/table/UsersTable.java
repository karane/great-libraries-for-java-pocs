package org.karane.sqlengine.table;

import org.apache.calcite.DataContext;
import org.apache.calcite.linq4j.Enumerable;
import org.apache.calcite.linq4j.Linq4j;
import org.apache.calcite.rel.type.RelDataType;
import org.apache.calcite.rel.type.RelDataTypeFactory;
import org.apache.calcite.schema.ScannableTable;
import org.apache.calcite.schema.impl.AbstractTable;
import org.karane.sqlengine.model.User;

import java.util.List;

public class UsersTable extends AbstractTable implements ScannableTable {

    private static final List<User> USERS = List.of(
        new User(1, "Alice", 34),
        new User(2, "Bob", 28),
        new User(3, "Carol", 41)
    );

    @Override
    public RelDataType getRowType(RelDataTypeFactory typeFactory) {
        return typeFactory.builder()
            .add("ID", typeFactory.createJavaType(int.class))
            .add("NAME", typeFactory.createJavaType(String.class))
            .add("AGE", typeFactory.createJavaType(int.class))
            .build();
    }

    @Override
    public Enumerable<Object[]> scan(DataContext root) {
        return Linq4j.asEnumerable(
            USERS.stream()
                .map(u -> new Object[]{u.id(), u.name(), u.age()})
                .toList()
        );
    }
}
