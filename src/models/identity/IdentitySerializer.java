package models.identity;

import models.metamodels.MetaField;
import models.metamodels.MetaFieldList;
import models.metamodels.MetaModel;

import java.util.HashMap;
import java.util.concurrent.Callable;

public class IdentitySerializer {

    private final Callable<Identity> factory;
    private final MetaModel metaModel;

    public IdentitySerializer(Callable<Identity> factory, MetaModel metaModel) {
        this.factory = factory;
        this.metaModel = metaModel;
    }

    public Identity serialize(HashMap<String, Object> map) throws Exception {
        Identity model = factory.call();
        MetaFieldList metaFields = metaModel.getMetaFieldList();
        for (MetaField column : metaFields.getColumns()) {
            column.assign(model, map.get(column.getName()));
        }
        return model;
    }

    public HashMap<String, Object> deserialize(Identity model) {
        HashMap<String, Object> map = new HashMap<>();
        MetaFieldList metaFields = metaModel.getMetaFieldList();
        for (MetaField column : metaFields.getColumns()) {
            map.put(column.getName(), column.retrieve(model));
        }
        return map;
    }
    
}
