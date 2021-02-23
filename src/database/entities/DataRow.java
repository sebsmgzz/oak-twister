package database.entities;

import java.util.Arrays;

public class DataRow {

    private final Object[] data;

    public boolean set(int i, Object value) {
        if(0 <= i && i < data.length) {
            data[i] = value;
            return true;
        }
        return false;
    }

    public Object get(int i) {
        if(0 <= i && i < data.length) {
            return data[i];
        }
        return null;
    }

    public DataRow(int size) {
        this.data = new Object[size];
        Arrays.fill(data, null);
    }

}
