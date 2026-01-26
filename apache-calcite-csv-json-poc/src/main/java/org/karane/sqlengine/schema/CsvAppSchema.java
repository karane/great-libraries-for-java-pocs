package org.karane.sqlengine.schema;

import org.apache.calcite.adapter.csv.CsvSchema;
import org.apache.calcite.adapter.csv.CsvTable;


import java.io.File;

public class CsvAppSchema extends CsvSchema {

    public CsvAppSchema() {
        super(
            new File("data/csv"),
            CsvTable.Flavor.SCANNABLE);
    }
}
