package database;

import java.sql.*;

public class QuerySet implements QueryResult {

    private final ResultSet resultSet;

    public QuerySet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    public boolean next() {
        try {
            return resultSet.next();
        } catch (SQLException e) {
            return false;
        }
    }

    public int getInt(String columnName) throws SQLException {
        return resultSet.getInt(columnName);
    }

    public String getString(String columnName) throws SQLException {
        return resultSet.getString(columnName);
    }

    public void finalize() {
        try {
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
