package middleware.metadata;

import middleware.annotations.Table;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MetaModel {

    private final Table table;
    public final List<MetaField> metaFields;

    public String getTableName() {
        return table.name();
    }

    public MetaModel(Class<?> type) {
        this.table = type.getAnnotation(Table.class);
        this.metaFields = new ArrayList<>();
        for (Field field: type.getDeclaredFields()) {
            metaFields.add(new MetaField(field));
        }
    }

}
