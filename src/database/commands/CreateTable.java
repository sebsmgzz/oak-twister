package database.commands;

import database.metaentities.Column;

import java.util.ArrayList;
import java.util.List;

public class CreateTable implements BaseCommand {

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
        List<String> strColumns = new ArrayList<>();
        columns.forEach(c -> strColumns.add(c.toString()));
        return "CREATE TABLE " + tableName + " (" + String.join(", ", strColumns) + ");";
    }

}
