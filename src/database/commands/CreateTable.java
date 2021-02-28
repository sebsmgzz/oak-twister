package database.commands;

import database.entities.DataType;

import java.util.ArrayList;
import java.util.List;

public class CreateTable implements BaseCommand {

    private final String tableName;
    private final List<String> columnNames;
    private final List<Integer> columnTypes;
    private final List<Boolean> columnNotNull;
    private final List<Boolean> columnPK;

    public String getTableName() {
        return tableName;
    }

    public CreateTable(String tableName) {
        this.tableName = tableName;
        this.columnNames = new ArrayList<String>();
        this.columnTypes = new ArrayList<Integer>();
        this.columnNotNull = new ArrayList<Boolean>();
        this.columnPK = new ArrayList<Boolean>();
    }

    public void addColumn(String name, int type, boolean notNull, boolean isPK) {
        columnNames.add(name);
        columnTypes.add(type);
        columnNotNull.add(notNull);
        columnPK.add(isPK);
    }

    @Override
    public String getQuery() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < columnNames.size(); i++) {
            builder.append(columnNames.get(i)).append(" ");
            builder.append(DataType.toString(columnTypes.get(i))).append(" ");
            builder.append(columnPK.get(i)? "PRIMARY KEY" : "").append(" ");
            builder.append(columnNotNull.get(i) ? "" : "NOT NULL").append(" ");
            builder.append(1 + i == columnNames.size() ? "" : ", ");
        }
        return "CREATE TABLE " + tableName + " (" + builder.toString() + ");";
    }

}
