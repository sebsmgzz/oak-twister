package middleware.callers;

import database.DbConnection;
import database.Statement;
import middleware.metadata.MetaModel;
import middleware.metadata.MetaField;

import java.sql.SQLException;

public class CreateTable {

    private MetaModel metaModel;

    public CreateTable(MetaModel metaModel) {
        this.metaModel = metaModel;
    }

    public boolean execute() {
        try {
            DbConnection connection = new DbConnection();
            database.commands.CreateTable command = new database.commands.CreateTable(metaModel.getTableName());
            for (MetaField metaField : metaModel.metaFields) {
                command.addColumn(
                        metaField.column.name(),
                        metaField.column.type(),
                        metaField.column.notNull(),
                        metaField.column.primaryKey());
            }
            Statement statement = connection.getStatement(command);
            statement.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

}
