package org.karane.sqlengine.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class ResultSetPrinter {

    public static void print(ResultSet rs) throws Exception {
        ResultSetMetaData meta = rs.getMetaData();
        int cols = meta.getColumnCount();

        // compute max width for each column
        int[] widths = new int[cols];
        String[][] cache = new String[1000][]; // simple cache
        int rowCount = 0;

        for (int i = 0; i < cols; i++) {
            widths[i] = meta.getColumnLabel(i + 1).length();
        }

        while (rs.next()) {
            String[] row = new String[cols];
            for (int i = 0; i < cols; i++) {
                String val = String.valueOf(rs.getObject(i + 1));
                row[i] = val;
                widths[i] = Math.max(widths[i], val.length());
            }
            cache[rowCount++] = row;
        }

        // header
        for (int i = 0; i < cols; i++) {
            System.out.print(pad(meta.getColumnLabel(i + 1), widths[i]) + " | ");
        }
        System.out.println();

        // separator
        for (int i = 0; i < cols; i++) {
            System.out.print("-".repeat(widths[i]) + "-+-");
        }
        System.out.println();

        // rows
        for (int r = 0; r < rowCount; r++) {
            for (int c = 0; c < cols; c++) {
                System.out.print(pad(cache[r][c], widths[c]) + " | ");
            }
            System.out.println();
        }
    }

    private static String pad(String s, int width) {
        return s + " ".repeat(Math.max(0, width - s.length()));
    }
}
