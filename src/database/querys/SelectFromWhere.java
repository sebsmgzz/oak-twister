package database.querys;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SelectFromWhere implements BaseQuery {

    private final String[] columnNames;
    private final String tableName;
    private final String columnName;
    private final int columnValue;

    public SelectFromWhere(String tableName, String columnName, int columnValue) {
        this(new String[]{"*"}, tableName, columnName, columnValue);
    }

    public SelectFromWhere(String[] columnNames, String tableName, String columnName, int columnValue) {
        this.columnNames = columnNames;
        this.tableName = tableName;
        this.columnName = columnName;
        this.columnValue = columnValue;
    }

    @Override
    public String getQuery() {
        return "SELECT ? FROM ? WHERE ? = ?";
    }

    @Override
    public void setParameters(PreparedStatement statement) throws SQLException {
        statement.setString(1, String.join(", ", columnNames));
        statement.setString(2, tableName);
        statement.setString(3, columnName);
        // TODO: refactor where clause
        statement.setInt(4, columnValue);
    }

}
