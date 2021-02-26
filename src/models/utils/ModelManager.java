package models.utils;

import database.entities.EntityTable;
import database.DbConnection;
import database.Statement;
import database.metaentities.Column;
import database.statements.BaseStatement;
import database.statements.CreateTable;
import database.statements.SelectFrom;
import database.statements.SelectFromWhere;
import models.metamodels.MetaField;
import models.metamodels.MetaFieldList;
import models.metamodels.MetaModel;

import java.sql.SQLException;

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
            Statement statement = connection.getStatement(createTableStatement);
            statement.execute();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public EntityTable selectAll() {
        try {
            DbConnection connection = new DbConnection();
            BaseStatement baseStatement = new SelectFrom(metaModel.getTableName());
            Statement statement = connection.getStatement(baseStatement);
            return statement.executeQuery();
        } catch (SQLException e) {
            return null;
        }
    }

    protected EntityTable select(int id) {
        try {
            DbConnection connection = new DbConnection();
            BaseStatement baseStatement = new SelectFromWhere(metaModel.getTableName(), "id", id);
            Statement statement = connection.getStatement(baseStatement);
            return statement.executeQuery();
        } catch (SQLException e) {
            return null;
        }
    }

}
