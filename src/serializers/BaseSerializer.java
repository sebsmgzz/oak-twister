package serializers;

import metamodels.MetaField;
import metamodels.MetaFieldList;
import metamodels.MetaModel;
import models.BaseModel;

import java.util.HashMap;
import java.util.Map;

public class BaseSerializer {

    private final BaseModel model;

    public BaseSerializer(BaseModel model) {
        this.model = model;
    }

    public void serialize(Map<String, Object> map) {
        MetaModel metaModel = model.getModelMeta();
        MetaFieldList metaFieldList = metaModel.getMetaFieldList();
        for(MetaField metaField : metaFieldList.getColumns()) {
            Object value = map.get(metaField.column.name());
            metaField.assign(model, value);
        }
    }

    public Map<String, Object> deserialize() {
        Map<String, Object> map = new HashMap<>();
        MetaModel metaModel = model.getModelMeta();
        MetaFieldList metaFieldList = metaModel.getMetaFieldList();
        for(MetaField metaField : metaFieldList.getColumns()) {
            Object value = metaField.retrieve(model);
            map.put(metaField.column.name(), value);
        }
        return map;
    }

}
