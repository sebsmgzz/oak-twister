package managers;

import database.entities.EntityTable;
import database.executable.DbConnection;
import database.executable.ExecutableStatement;
import database.executable.QueryResult;
import database.metaentities.Column;
import database.statements.BaseStatement;
import database.statements.CreateTable;
import database.statements.SelectFrom;
import database.statements.SelectFromWhere;
import metamodels.MetaField;

import java.sql.SQLException;
import java.util.List;

public abstract class BaseManager {

    protected boolean createTable(String tableName, List<MetaField> metaFields) {
        try {
            DbConnection connection = new DbConnection();
            CreateTable createTableStatement = new CreateTable(tableName);
            for (MetaField metaField : metaFields) {
                createTableStatement.addColumn(new Column(
                        metaField.column.name(),
                        metaField.column.type(),
                        metaField.column.notNull(),
                        metaField.column.primaryKey()));
            }
            ExecutableStatement executableStatement = connection.getStatement(createTableStatement);
            return executableStatement.execute();
        } catch (Exception e) {
            return false;
        }
    }

    protected EntityTable executeSelectAll(String tableName) {
        try {
            DbConnection connection = new DbConnection();
            BaseStatement baseStatement = new SelectFrom(tableName);
            ExecutableStatement executableStatement = connection.getStatement(baseStatement);
            QueryResult result = executableStatement.executeQuery();
            return result.getAll();
        } catch (SQLException e) {
            return null;
        }
    }

    protected EntityTable executeSelect(String tableName, int id) {
        try {
            DbConnection connection = new DbConnection();
            BaseStatement baseStatement = new SelectFromWhere(tableName, "id", id);
            ExecutableStatement executableStatement = connection.getStatement(baseStatement);
            QueryResult result = executableStatement.executeQuery();
            return result.getNext();
        } catch (SQLException e) {
            return null;
        }
    }

}
