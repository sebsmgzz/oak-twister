package models.platform;

import models.metamodels.MetaField;
import models.metamodels.MetaFieldList;
import models.metamodels.MetaModel;

import java.util.HashMap;
import java.util.concurrent.Callable;

public class PlatformSerializer {

    private final Callable<Platform> factory;
    private final MetaModel metaModel;

    public PlatformSerializer(Callable<Platform> factory, MetaModel metaModel) {
        this.factory = factory;
        this.metaModel = metaModel;
    }

    public Platform serialize(HashMap<String, Object> map) throws Exception {
        Platform model = factory.call();
        MetaFieldList metaFields = metaModel.getMetaFieldList();
        for (MetaField column : metaFields.getColumns()) {
            column.assign(model, map.get(column.getName()));
        }
        return model;
    }

    public HashMap<String, Object> deserialize(Platform model) {
        HashMap<String, Object> map = new HashMap<>();
        MetaFieldList metaFields = metaModel.getMetaFieldList();
        for (MetaField column : metaFields.getColumns()) {
            map.put(column.getName(), column.retrieve(model));
        }
        return map;
    }

}
