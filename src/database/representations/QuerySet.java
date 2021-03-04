package database.representations;

import java.awt.*;
import java.sql.*;

public class QuerySet implements QueryResult {

    private final ResultSet resultSet;

    public String getColumnNames(int index) throws SQLException {
        return resultSet.getMetaData().getColumnName(index);
    }

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

    @Override
    public int getInt(String columnName) throws SQLException {
        return resultSet.getInt(columnName);
    }

    @Override
    public String getString(String columnName) throws SQLException {
        return resultSet.getString(columnName);
    }

    @Override
    public Object getObject(String columnName) throws SQLException {
        return resultSet.getObject(columnName);
    }

    @Override
    public Date getDate(String columnName) throws SQLException {
        return resultSet.getDate(columnName);
    }

    @Override
    public Image getImage(String columnName) throws SQLException {
        return null; // TODO: retrieve image
    }

    public void finalize() {
        try {
            resultSet.close();
        } catch (SQLException ignored) {
        }
    }

}
