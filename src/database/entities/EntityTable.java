package database.entities;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class EntityTable implements Iterable<String> {

    private final DataTable data;
    private final List<String> names;
    private final List<Integer> types;

    public String[] getNames() {
        return names.toArray(new String[0]);
    }

    public Integer[] getTypes() {
        return types.toArray(new Integer[0]);
    }

    public String getName(int index) {
        if(0 <= index && index < names.size()) {
            return names.get(index);
        }
        return null;
    }

    public Integer getType(int index) {
        if(0 <= index && index < types.size()) {
            return types.get(index);
        }
        return null;
    }

    public Integer getType(String name) {
        return getType(names.indexOf(name));
    }

    public EntityTable(ResultSetMetaData metaData) throws SQLException {
        this.data = new DataTable(metaData.getColumnCount());
        this.names = new ArrayList<>();
        this.types = new ArrayList<>();
        for(int i = 0; i < metaData.getColumnCount(); i++) {
            this.names.add(metaData.getColumnName(i + 1));
            this.types.add(metaData.getColumnType(i + 1));
        }
    }

    public DataRow addDataRow() {
        return data.addRow();
    }

    public void populate(HashMap<String, Object> map) {
        DataRow row = data.addRow();
        for (int i = 0; i < names.size(); i++) {
            String name = names.get(i);
            if (map.containsKey(name)) {
                row.set(i, map.get(name));
            }
        }
    }

    @Override
    public Iterator<String> iterator() {
        return names.iterator();
    }

}
