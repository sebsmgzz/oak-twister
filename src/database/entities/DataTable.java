package database.entities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataTable implements Iterable<DataRow> {

    private final int columnCount;
    private final List<DataRow> data;

    public int size() {
        return data.size();
    }

    public DataRow get(int rowIndex) {
        if(0 <= rowIndex && rowIndex < data.size()) {
            return data.get(rowIndex);
        }
        return null;
    }

    public DataTable(int columnCount) {
        this.columnCount = columnCount;
        this.data = new ArrayList<>();
    }

    public DataRow addRow() {
        DataRow row = new DataRow(columnCount);
        data.add(row);
        return row;
    }

    @Override
    public Iterator<DataRow> iterator() {
        return data.iterator();
    }

}
