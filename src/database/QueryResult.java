package database;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;

public class QueryResult {

    private final ResultSetMetaData metaData;
    private final ResultSet resultSet;

    public int getColumnCount() throws SQLException {
        return metaData.getColumnCount();
    }

    public String getColumnName(int index) throws SQLException {
        return metaData.getColumnName(index);
    }

    public QueryResult(ResultSet resultSet) throws SQLException {
        this.metaData = resultSet.getMetaData();
        this.resultSet = resultSet;
    }

    public boolean next() throws SQLException {
        return resultSet.next();
    }

    public Object getValue(int columnIndex) throws SQLException {
        return resultSet.getObject(columnIndex);
    }

    public Object getValue(String columnName) throws SQLException {
        return resultSet.getObject(columnName);
    }

    public HashMap<String, Object> getAllValues() throws SQLException {
        HashMap<String, Object> values = new HashMap<>();
        for (int i = 0; i < metaData.getColumnCount(); i++) {
            String key = metaData.getColumnName(i + 1);
            Object value = resultSet.getObject(i+ 1);
            values.put(key, value);
        }
        return values;
    }

    public void finalize() {
        // TODO: fix deprecated destructor
        try {
            resultSet.close();
        } catch (SQLException ignored) {
        }
    }

}
