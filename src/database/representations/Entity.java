package database.representations;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Entity implements Iterable<Object[]> {

    private final List<String> columnNames;
    private final List<Integer> columnTypes;
    private final List<Object[]> columnValues;

    public int rowCount() {
        return columnValues.size();
    }

    public int columnCount() {
        return columnNames.size();
    }

    public HashMap<String, Object> getMap(int rowIndex) {
        if(-1 < rowIndex && rowIndex < columnValues.size()) {
            HashMap<String, Object> map = new HashMap<>();
            Object[] values = columnValues.get(rowIndex);
            for (int i = 0; i < values.length; i++) {
                map.put(columnNames.get(i), values[i]);
            }
            return map;
        }
        return null;
    }

    public Entity() {
        this.columnNames = new ArrayList<>();
        this.columnTypes = new ArrayList<>();
        this.columnValues = new ArrayList<>();
    }

    public void populate(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        for(int i = 0; i < metaData.getColumnCount(); i++) {
            columnNames.add(metaData.getColumnName(i + 1));
            columnTypes.add(metaData.getColumnType(i + 1));
        }
        while (resultSet.next()) {
            Object[] values = new Object[columnCount];
            for (int i = 0; i < columnCount; i++) {
                values[i] = resultSet.getObject(i + 1);
            }
            columnValues.add(values);
        }
    }

    @Override
    public Iterator<Object[]> iterator() {
        return columnValues.iterator();
    }

}
