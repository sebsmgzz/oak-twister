package metadata;

import annotations.Column;
import annotations.ForeignKey;

import java.lang.reflect.Field;

public class MetaField {

    private final Field field;
    public final Column column;
    public final ForeignKey foreignKey;

    public String getName() {
        return field.getName();
    }

    public MetaField(Field field) {
        field.setAccessible(true);
        this.field = field;
        this.column = field.getAnnotation(Column.class);
        this.foreignKey = field.getAnnotation(ForeignKey.class);
    }

    public Object retrieve(Object model) {
        try {
            return field.get(model);
        } catch (IllegalAccessException e) {
            return null;
        }
    }

    public boolean assign(Object model, Object value) {
        try {
            field.set(model, value);
            return true;
        } catch (IllegalAccessException e) {
            return false;
        }
    }

}
