package middleware.callers;

import database.DbConnection;
import database.QuerySet;
import database.Statement;
import database.commands.BaseCommand;
import database.commands.SelectWhere;
import middleware.metadata.MetaModel;

import java.sql.SQLException;

public class SelectWhere {

    private MetaModel metaModel;
    private String columnName;
    private int value;

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public SelectWhere(MetaModel metaModel) {
        this.metaModel = metaModel;
    }

    public QuerySet execute() {
        try {
            DbConnection connection = new DbConnection();
            BaseCommand baseCommand = new database.commands.SelectWhere(metaModel.getTableName(), columnName, value);
            Statement statement = connection.getStatement(baseCommand);
            return statement.executeQuery();
        } catch (SQLException e) {
            return null;
        }
    }

}
