package models.utils;

import models.metamodels.MetaField;
import models.metamodels.MetaFieldList;
import models.metamodels.MetaModel;
import models.BaseModel;
import models.ModelFactory;

import java.util.HashMap;

public class ModelSerializer<T extends BaseModel> {

    private final Class<T> type;
    private final ModelFactory modelFactory;
    private final MetaModel metaModel;

    public ModelSerializer(Class<T> type) {
        this.type = type;
        this.modelFactory = new ModelFactory();
        this.metaModel = new MetaModel(type);
    }

    public T serialize(HashMap<String, Object> map) {
        T model = modelFactory.get(type);
        MetaFieldList metaFields = metaModel.getMetaFieldList();
        for (MetaField column : metaFields.getColumns()) {
            column.assign(model, map.get(column.getName()));
        }
        return model;
    }

    public HashMap<String, Object> deserialize(T model) {
        HashMap<String, Object> map = new HashMap<>();
        MetaFieldList metaFields = metaModel.getMetaFieldList();
        for (MetaField column : metaFields.getColumns()) {
            map.put(column.getName(), column.retrieve(model));
        }
        return map;
    }

}
