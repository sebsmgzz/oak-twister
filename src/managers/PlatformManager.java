package managers;

import database.entities.EntityTable;
import metamodels.MetaModel;
import models.Platform;
import serializers.PlatformSerializer;

import java.util.ArrayList;
import java.util.List;

public class PlatformManager extends BaseManager {

    private final MetaModel metaModel;
    private final PlatformSerializer serializer;

    public PlatformManager(MetaModel metaModel, PlatformSerializer serializer) {
        this.metaModel = metaModel;
        this.serializer = serializer;
    }

    public boolean createTable() {
        return super.createTable(
                metaModel.getTableName(),
                metaModel.getMetaFieldList().getColumns());
    }

    public List<Platform> selectAll() throws Exception {
        List<Platform> models = new ArrayList<>();
        EntityTable entityTable = super.executeSelectAll(metaModel.getTableName());
        if(entityTable != null) {
            for(int i = 0; i < entityTable.getRowCount(); i++) {
                models.add(serializer.serialize(entityTable.mapRow(i)));
            }
        }
        return models;
    }

    public Platform select(int id) throws Exception {
        EntityTable entityTable = super.executeSelect(metaModel.getTableName(), id);
        if(entityTable != null) {
            return serializer.serialize(entityTable.mapRow(0));
        }
        return null;
    }
    
}
