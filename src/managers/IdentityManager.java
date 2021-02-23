package managers;

import database.entities.EntityTable;
import metamodels.MetaModel;
import models.Identity;
import serializers.IdentitySerializer;

import java.util.ArrayList;
import java.util.List;

public class IdentityManager extends BaseManager {

    private final MetaModel metaModel;
    private final IdentitySerializer serializer;

    public IdentityManager(MetaModel metaModel, IdentitySerializer identitySerializer) {
        this.metaModel = metaModel;
        this.serializer = identitySerializer;
    }

    public boolean createTable() {
        return super.createTable(
                metaModel.getTableName(),
                metaModel.getMetaFieldList().getColumns());
    }

    public List<Identity> selectAll() throws Exception {
        List<Identity> models = new ArrayList<>();
        EntityTable entityTable = super.executeSelectAll(metaModel.getTableName());
        if(entityTable != null) {
            for(int i = 0; i < entityTable.getRowCount(); i++) {
                models.add(serializer.serialize(entityTable.mapRow(i)));
            }
        }
        return models;
    }

    public Identity select(int id) throws Exception {
        EntityTable entityTable = super.executeSelect(metaModel.getTableName(), id);
        if(entityTable != null) {
            return serializer.serialize(entityTable.mapRow(0));
        }
        return null;
    }

}
