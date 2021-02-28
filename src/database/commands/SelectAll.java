package database.commands;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SelectAll implements BaseCommand {

    private final String[] columnNames;
    private final String tableName;

    public SelectAll(String tableName) {
        this(new String[]{"*"}, tableName);
    }

    public SelectAll(String[] columnNames, String tableName) {
        this.columnNames = columnNames;
        this.tableName = tableName;
    }

    @Override
    public String getQuery() {
        return "SELECT ? FROM ? ;";
    }

    @Override
    public void setParameters(PreparedStatement statement) throws SQLException {
        statement.setString(1, String.join(", ", columnNames));
        statement.setString(2, tableName);
    }

}
