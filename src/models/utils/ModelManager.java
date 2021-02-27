package models.utils;

import database.entities.Entity;
import database.DbConnection;
import database.Statement;
import database.metaentities.Column;
import database.commands.BaseCommand;
import database.commands.CreateTable;
import database.commands.SelectFrom;
import database.commands.SelectFromWhere;
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

    public Entity selectAll() {
        try {
            DbConnection connection = new DbConnection();
            BaseCommand baseCommand = new SelectFrom(metaModel.getTableName());
            Statement statement = connection.getStatement(baseCommand);
            return statement.executeQuery();
        } catch (SQLException e) {
            return null;
        }
    }

    protected Entity select(int id) {
        try {
            DbConnection connection = new DbConnection();
            BaseCommand baseCommand = new SelectFromWhere(metaModel.getTableName(), "id", id);
            Statement statement = connection.getStatement(baseCommand);
            return statement.executeQuery();
        } catch (SQLException e) {
            return null;
        }
    }

}
