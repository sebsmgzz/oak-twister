package models.metamodels;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MetaFieldList {

    private final List<MetaField> metaFields;

    public List<MetaField> getColumns() {
        List<MetaField> columns = new ArrayList<>();
        for(MetaField metaField : metaFields) {
            if(metaField.column != null) {
                columns.add(metaField);
            }
        }
        return columns;
    }

    public MetaFieldList(Field[] fields) {
        this(fields, false);
    }

    public MetaFieldList(Field[] fields, boolean includeNullAnnotations) {
        this.metaFields = new ArrayList<>();
        for(Field field : fields) {
            MetaField metaField = new MetaField(field);
            if(metaField.column != null || includeNullAnnotations) {
                metaFields.add(new MetaField(field));
            }
        }
    }

}
