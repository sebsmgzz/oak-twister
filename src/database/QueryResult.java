package database;

import java.awt.Image;
import java.sql.SQLException;
import java.util.Date;

public interface QueryResult {

    int getInt(String columnName) throws SQLException;

    String getString(String columnName) throws SQLException;

    Object getObject(String columnName) throws SQLException;

    Date getDate(String columnName) throws SQLException;

    Image getImage(String columnName) throws SQLException;

}
