package models;

import annotations.Table;
import metamodels.MetaModel;
import managers.BaseManager;
import serializers.BaseSerializer;

@Table(name = "")
public abstract class BaseModel {

    private MetaModel modelMeta;
    private BaseSerializer serializer;
    private BaseManager manager;

    public MetaModel getModelMeta() {
        if(modelMeta == null) {
            modelMeta = new MetaModel(this.getClass());
        }
        return modelMeta;
    }

    public BaseSerializer getSerializer() {
        if(serializer == null) {
            serializer = new BaseSerializer(this);
        }
        return serializer;
    }

    public BaseManager getManager() {
        if(manager == null) {
            manager = new BaseManager(this);
        }
        return manager;
    }

    public abstract BaseModel getNew();

    @Override
    public abstract String toString();

}
