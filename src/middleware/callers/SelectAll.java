package middleware.callers;

import database.DbConnection;
import database.QuerySet;
import database.Statement;
import database.commands.BaseCommand;
import database.commands.SelectFrom;
import middleware.metadata.MetaModel;

import java.sql.SQLException;

public class SelectAll {

    private final MetaModel metaModel;

    public SelectAll(MetaModel metaModel) {
        this.metaModel = metaModel;
    }

    public QuerySet execute() {
        try {
            DbConnection connection = new DbConnection();
            BaseCommand baseCommand = new SelectFrom(metaModel.getTableName());
            Statement statement = connection.getStatement(baseCommand);
            return statement.executeQuery();
        } catch (SQLException e) {
            return null;
        }
    }

}
