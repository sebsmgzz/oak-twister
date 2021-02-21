package database.statements;

import java.sql.Statement;

public class SelectAll extends BaseStatement {

    private String tableName;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public SelectAll(Statement statement) {
        super(statement);
    }

    @Override
    public String getQuery() {
        return "SELECT * FROM " + tableName + ";";
    }

}
