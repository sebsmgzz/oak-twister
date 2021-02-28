package models;

import database.QuerySet;
import middleware.callers.CreateTable;
import middleware.callers.SelectAll;
import middleware.callers.SelectWhere;
import middleware.metadata.MetaModel;

import java.util.ArrayList;
import java.util.List;

public abstract class Manager<T extends Model> {

    private final MetaModel metaModel;
    private final Serializer<T> serializer;

    public Manager(MetaModel metaModel, Serializer<T> serializer) {
        this.metaModel = metaModel;
        this.serializer = serializer;
    }

    public boolean createTable() {
        CreateTable createTable = new CreateTable(metaModel);
        return createTable.execute();
    }

    public List<T> selectAll() {
        List<T> models = new ArrayList<>();
        SelectAll command = new SelectAll(metaModel);
        QuerySet querySet = command.execute();
        while(querySet.next()) {
            T model = serializer.serialize(querySet);
            models.add(model);
        }
        return models;
    }

    protected T selectWhere(String columnName, int value) {
        SelectWhere command = new SelectWhere(metaModel);
        command.setColumnName(columnName);
        command.setValue(value);
        QuerySet querySet = command.execute();
        if(querySet.next()) {
            return serializer.serialize(querySet);
        }
        return null;
    }

}
