package models.password;

import models.metamodels.MetaField;
import models.metamodels.MetaFieldList;
import models.metamodels.MetaModel;

import java.util.HashMap;
import java.util.concurrent.Callable;

public class PasswordSerializer {

    private final Callable<Password> factory;
    private final MetaModel metaModel;

    public PasswordSerializer(Callable<Password> factory, MetaModel metaModel) {
        this.factory = factory;
        this.metaModel = metaModel;
    }

    public Password serialize(HashMap<String, Object> map) throws Exception {
        Password model = factory.call();
        MetaFieldList metaFields = metaModel.getMetaFieldList();
        for (MetaField column : metaFields.getColumns()) {
            column.assign(model, map.get(column.getName()));
        }
        return model;
    }

    public HashMap<String, Object> deserialize(Password model) {
        HashMap<String, Object> map = new HashMap<>();
        MetaFieldList metaFields = metaModel.getMetaFieldList();
        for (MetaField column : metaFields.getColumns()) {
            map.put(column.getName(), column.retrieve(model));
        }
        return map;
    }
    
}
