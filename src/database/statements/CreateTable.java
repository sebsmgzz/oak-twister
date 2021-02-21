package database.statements;

import database.Column;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CreateTable extends BaseStatement {

    private String tableName;
    private final List<Column> columns;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public CreateTable(Statement statement) {
        super(statement);
        this.columns = new ArrayList<>();
    }

    public boolean addColumn(Column column) {
        return columns.add(column);
    }

    public boolean removeColumn(Column column) {
        return columns.remove(column);
    }

    @Override
    public String getQuery() {
        List<String> strColumns = new ArrayList<>();
        columns.forEach(c -> strColumns.add(c.toString()));
        return "CREATE TABLE " + tableName +
               "(" + String.join(", ", strColumns) + ");";
    }

}
