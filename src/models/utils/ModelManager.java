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

public class  ModelManager<T extends BaseModel> {

    private final Class<T> type;
    private final ModelFactory modelFactory;
    private final ModelSerializer<T> serializer;
    private final MetaModel metaModel;

    public ModelManager(Class<T> type) {
        this.type = type;
        this.modelFactory = new ModelFactory();
        this.serializer = new ModelSerializer<T>(type);
        this.metaModel = new MetaModel(type);
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

    public List<T> selectAll() {
        List<T> models = new ArrayList<>();
        try {
            DbConnection connection = new DbConnection();
            BaseStatement baseStatement = new SelectFrom(metaModel.getTableName());
            ExecutableStatement executableStatement = connection.getStatement(baseStatement);
            QueryResult result = executableStatement.executeQuery();
            EntityTable entityTable =  result.getAll();
            if(entityTable != null) {
                for(int i = 0; i < entityTable.getRowCount(); i++) {
                    T model = serializer.serialize(entityTable.mapRow(i));
                    models.add(model);
                }
            }
            return models;
        } catch (SQLException e) {
            return null;
        }
    }

    protected T select(int id) {
        try {
            DbConnection connection = new DbConnection();
            BaseStatement baseStatement = new SelectFromWhere(metaModel.getTableName(), "id", id);
            ExecutableStatement executableStatement = connection.getStatement(baseStatement);
            QueryResult result = executableStatement.executeQuery();
            EntityTable entityTable = result.getNext();
            if(entityTable != null) {
                return serializer.serialize(entityTable.mapRow(0));
            }
            return null;
        } catch (SQLException e) {
            return null;
        }
    }

}
