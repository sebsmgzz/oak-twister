package database.executable;

import database.entities.DataRow;
import database.entities.EntityTable;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class QueryResult {

    private final ResultSetMetaData metaData;
    private final ResultSet resultSet;

    public QueryResult(ResultSet resultSet) throws SQLException {
        this.metaData = resultSet.getMetaData();
        this.resultSet = resultSet;
    }

    public boolean next() throws SQLException {
        return resultSet.next();
    }

    public EntityTable get() throws SQLException {
        EntityTable entityTable = new EntityTable(metaData);
        DataRow row = entityTable.addDataRow();
        for (int i = 0; i < metaData.getColumnCount(); i++) {
            Object value = resultSet.getObject(i+ 1);
            row.set(i, value);
        }
        return entityTable;
    }

    public EntityTable getNext() throws SQLException {
        return resultSet.next()? get() : null;
    }

    public EntityTable getAll() throws SQLException {
        EntityTable entityTable = new EntityTable(metaData);
        while (resultSet.next()) {
            DataRow row = entityTable.addDataRow();
            for (int i = 0; i < metaData.getColumnCount(); i++) {
                Object value = resultSet.getObject(i+ 1);
                row.set(i, value);
            }
        }
        return entityTable;
    }

    public void finalize() {
        // TODO: fix deprecated destructor
        try {
            resultSet.close();
        } catch (SQLException ignored) {
        }
    }

}
