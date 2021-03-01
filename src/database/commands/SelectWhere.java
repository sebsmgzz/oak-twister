package database.commands;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SelectWhere implements BaseCommand {

    private final String tableName;
    private final String columnName;
    private final int columnValue;

    public SelectWhere(String tableName, String columnName, int columnValue) {
        this.tableName = tableName;
        this.columnName = columnName;
        this.columnValue = columnValue;
    }

    @Override
    public String getQuery() {
        return "SELECT * FROM ? WHERE ? = ?";
    }

    @Override
    public void setParameters(PreparedStatement statement) throws SQLException {
        statement.setString(1, tableName);
        statement.setString(2, columnName);
        statement.setInt(3, columnValue);
    }

}
