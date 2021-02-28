package metadata;

import annotations.Table;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MetaModelBase {

    private final Table table;
    public final List<MetaField> metaFields;

    public String getTableName() {
        return table.name();
    }

    public MetaModelBase(Class<?> type) {
        this.table = type.getAnnotation(Table.class);
        this.metaFields = new ArrayList<>();
        for (Field field: type.getFields()) {
            metaFields.add(new MetaField(field));
        }
    }

}
