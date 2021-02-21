package database.statements;

import database.clauses.Where;

import java.sql.Statement;

public class Select extends BaseStatement {

    private String tableName;
    private Where where;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setWhere(Where where) {
        this.where = where;
    }

    public Select(Statement statement) {
        super(statement);
    }

    @Override
    public String getQuery() {
        return "SELECT * FROM " + tableName + " " + where.toString() + ";";
    }

}
