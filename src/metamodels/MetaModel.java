package metamodels;

import annotations.Table;
import models.BaseModel;

import java.lang.reflect.Field;

public class MetaModel {

    private final MetaFieldList metaFieldList;
    private final Table table;

    public String getTableName() {
        return table.name();
    }

    public MetaFieldList getMetaFieldList() {
        return metaFieldList;
    }

    public MetaModel(Class<? extends BaseModel> type) {
        this.table = type.getAnnotation(Table.class);
        Field[] fields = type.getDeclaredFields();
        this.metaFieldList = new MetaFieldList(fields);
    }

}
