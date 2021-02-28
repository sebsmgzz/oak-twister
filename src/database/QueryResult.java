package database;

import java.sql.SQLException;

public interface QueryResult {

    int getInt(String columnName) throws SQLException;

    String getString(String columnName) throws SQLException;

}
