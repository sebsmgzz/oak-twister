package models.utils;

import database.entities.EntityTable;
import database.DbConnection;
import database.ExecutableStatement;
import database.QueryResult;
import database.metaentities.Column;
import database.statements.BaseStatement;
import database.statements.CreateTable;
import database.statements.SelectFrom;
import database.statements.SelectFromWhere;
import models.metamodels.MetaField;
import models.metamodels.MetaFieldList;
import models.metamodels.MetaModel;
import models.BaseModel;
import models.ModelFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class  ModelManager {

    private final MetaModel metaModel;

    public ModelManager(MetaModel metaModel) {
        this.metaModel = metaModel;
    }

    public boolean createTable() {
        try {
            DbConnection connection = new DbConnection();
            CreateTable createTableStatement = new CreateTable(metaModel.getTableName());
            MetaFieldList metaFields = metaModel.getMetaFieldList();
            for (MetaField metaField : metaFields.getColumns()) {
                createTableStatement.addColumn(new Column(
                        metaField.column.name(),
                        metaField.column.type(),
                        metaField.column.notNull(),
                        metaField.column.primaryKey()));
            }
            ExecutableStatement executableStatement = connection.getStatement(createTableStatement);
            executableStatement.execute();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public EntityTable selectAll() {
        try {
            DbConnection connection = new DbConnection();
            BaseStatement baseStatement = new SelectFrom(metaModel.getTableName());
            ExecutableStatement executableStatement = connection.getStatement(baseStatement);
            QueryResult result = executableStatement.executeQuery();
            return result.getAll();
        } catch (SQLException e) {
            return null;
        }
    }

    protected EntityTable select(int id) {
        try {
            DbConnection connection = new DbConnection();
            BaseStatement baseStatement = new SelectFromWhere(metaModel.getTableName(), "id", id);
            ExecutableStatement executableStatement = connection.getStatement(baseStatement);
            QueryResult result = executableStatement.executeQuery();
            return result.getNext();
        } catch (SQLException e) {
            return null;
        }
    }

}
