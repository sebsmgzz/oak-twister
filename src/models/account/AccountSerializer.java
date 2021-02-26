package models.account;

import models.metamodels.MetaField;
import models.metamodels.MetaFieldList;
import models.metamodels.MetaModel;

import java.util.HashMap;
import java.util.concurrent.Callable;

public class AccountSerializer {

    private final Callable<Account> factory;
    private final MetaModel metaModel;

    public AccountSerializer(Callable<Account> factory, MetaModel metaModel) {
        this.factory = factory;
        this.metaModel = metaModel;
    }

    public Account serialize(HashMap<String, Object> map) throws Exception {
        Account model = factory.call();
        MetaFieldList metaFields = metaModel.getMetaFieldList();
        for (MetaField column : metaFields.getColumns()) {
            column.assign(model, map.get(column.getName()));
        }
        return model;
    }

    public HashMap<String, Object> deserialize(Account model) {
        HashMap<String, Object> map = new HashMap<>();
        MetaFieldList metaFields = metaModel.getMetaFieldList();
        for (MetaField column : metaFields.getColumns()) {
            map.put(column.getName(), column.retrieve(model));
        }
        return map;
    }

}
