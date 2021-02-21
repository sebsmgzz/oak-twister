package database.statements;

import database.Column;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CreateTable implements BaseStatement {

    private final String tableName;
    private final List<Column> columns;

    public CreateTable(String tableName) {
        this.tableName = tableName;
        this.columns = new ArrayList<Column>();
    }

    public boolean addColumn(Column column) {
        return columns.add(column);
    }

    public boolean removeColumn(Column column) {
        return columns.remove(column);
    }

    @Override
    public String getQuery() {
        return "CREATE TABLE ? ( ? );";
    }

    @Override
    public void setParameters(PreparedStatement statement) throws SQLException {
        List<String> strColumns = new ArrayList<>();
        columns.forEach(c -> strColumns.add(c.toString()));
        statement.setString(1, tableName);
        statement.setString(2, String.join(", ", strColumns));
    }

}
