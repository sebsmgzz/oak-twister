package database.commands;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Insert implements BaseCommand {

    private final String tableName;
    private final List<String> columnNames;
    private final List<String> columnValues;


    public Insert(String tableName) {
        this.tableName = tableName;
        this.columnNames = new ArrayList<>();
        this.columnValues = new ArrayList<>();
    }

    public void add(String columnName, String columnValue) {
        columnNames.add(columnName);
        columnValues.add(columnValue);
    }

    @Override
    public String getQuery() {
        StringBuilder names = new StringBuilder();
        StringBuilder values = new StringBuilder();
        for (int i = 0; i < columnNames.size(); i++) {
            names.append(columnNames.get(i));
            names.append(1 + 1 == columnNames.size()? " " : ", ");
            values.append(" ? ");
            values.append(1 + 1 == columnNames.size()? " " : ", ");
        }
        return "INSERT INTO ? (" + names + ") VALUES (" + values + ");";
    }

    @Override
    public void setParameters(PreparedStatement statement) throws SQLException {
        statement.setString(1, tableName);
        for (int i = 0; i < columnValues.size(); i++) {
            statement.setString(2 + i, columnValues.get(i));
        }
    }

}
