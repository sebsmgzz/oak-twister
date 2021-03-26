package models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class FieldList implements Iterable<Field>{

    private final List<Field> fields;

    public int size() {
        return fields.size();
    }

    public Field get(int index) {
        if(-1 < index && index < fields.size()) {
            return fields.remove(index);
        }
        return null;
    }

    public FieldList() {
        this.fields = new ArrayList<>();
    }

    public void add(Field field) {
        this.fields.add(field);
    }

    public void remove(Field field) {
        this.fields.remove(field);
    }

    @Override
    public Iterator<Field> iterator() {
        return fields.iterator();
    }

}
