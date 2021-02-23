package managers;

import database.entities.EntityTable;
import metamodels.MetaModel;
import models.Password;
import serializers.PasswordSerializer;

import java.util.ArrayList;
import java.util.List;

public class PasswordManager extends BaseManager {

    private final MetaModel metaModel;
    private final PasswordSerializer serializer;

    public PasswordManager(MetaModel metaModel, PasswordSerializer serializer) {
        this.metaModel = metaModel;
        this.serializer = serializer;
    }

    public boolean createTable() {
        return super.createTable(
                metaModel.getTableName(),
                metaModel.getMetaFieldList().getColumns());
    }

    public List<Password> selectAll() throws Exception {
        List<Password> models = new ArrayList<>();
        EntityTable entityTable = super.executeSelectAll(metaModel.getTableName());
        if(entityTable != null) {
            for(int i = 0; i < entityTable.getRowCount(); i++) {
                models.add(serializer.serialize(entityTable.mapRow(i)));
            }
        }
        return models;
    }

    public Password select(int id) throws Exception {
        EntityTable entityTable = super.executeSelect(metaModel.getTableName(), id);
        if(entityTable != null) {
            return serializer.serialize(entityTable.mapRow(0));
        }
        return null;
    }
    
}
