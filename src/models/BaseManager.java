package models;

import database.entities.Entity;
import database.DbConnection;
import database.Statement;
import database.commands.BaseCommand;
import database.commands.CreateTable;
import database.commands.SelectFrom;
import database.commands.SelectFromWhere;
import metadata.MetaField;
import metadata.MetaModelBase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseManager<T extends BaseDataModel> {

    private final MetaModelBase metaModel;
    private final BaseSerializer<T> serializer;

    public BaseManager(MetaModelBase metaModel, BaseSerializer<T> serializer) {
        this.metaModel = metaModel;
        this.serializer = serializer;
    }

    public boolean createTable() {
        try {
            DbConnection connection = new DbConnection();
            CreateTable createTableStatement = new CreateTable(metaModel.getTableName());
            for (MetaField metaField : metaModel.metaFields) {
                createTableStatement.addColumn(
                    metaField.column.name(),
                    metaField.column.type(),
                    metaField.column.notNull(),
                    metaField.column.primaryKey());
            }
            Statement statement = connection.getStatement(createTableStatement);
            statement.execute();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<T> selectAll() {
        List<T> models = new ArrayList<>();
        try {
            DbConnection connection = new DbConnection();
            BaseCommand baseCommand = new SelectFrom(metaModel.getTableName());
            Statement statement = connection.getStatement(baseCommand);
            Entity entity = statement.executeQuery();
            if(entity != null) {
                for (int i = 0; i < entity.rowCount(); i++) {
                    T model = serializer.serialize(entity.getMap(i));
                    models.add(model);
                }
            }
        } catch (SQLException e) {
            return null;
        }
        return models;
    }

    protected T select(String columnName, int value) {
        try {
            DbConnection connection = new DbConnection();
            BaseCommand baseCommand = new SelectFromWhere(metaModel.getTableName(), columnName, value);
            Statement statement = connection.getStatement(baseCommand);
            Entity entity = statement.executeQuery();
            if(entity != null) {
                return serializer.serialize(entity.getMap(0));
            }
        } catch (SQLException e) {
            return null;
        }
        return null;
    }

}
