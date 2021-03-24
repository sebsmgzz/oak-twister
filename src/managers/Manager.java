package managers;

import database.DbConnection;
import database.GeneratedKey;
import database.Statement;
import database.representations.QueryResult;
import database.representations.QuerySet;
import models.Model;
import models.Serializer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class Manager<T extends Model> {

    protected final Serializer<T> serializer;

    public Manager(Serializer<T> serializer) {
        this.serializer = serializer;
    }

    protected abstract String getCreateTableQuery();
    public boolean createTable() {
        try {
            DbConnection connection = new DbConnection();
            Statement statement = connection.getStatement(getCreateTableQuery());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    protected abstract String getSelectAllQuery();
    public List<T> selectAll() {
        List<T> models = new ArrayList<>();
        try {
            DbConnection connection = new DbConnection();
            Statement statement  = connection.getStatement(getSelectAllQuery());
            QuerySet querySet = statement.executeQuery();
            while(querySet.next()) {
                T model = serializer.serialize(querySet);
                models.add(model);
            }
            return models;
        } catch (SQLException e) {
            return null;
        }
    }

    protected abstract String getInsertQuery();
    protected abstract void setInsertQueryParameters(Statement statement, T model) throws SQLException;
    protected abstract void setPrimaryKey(QueryResult queryResult, T model) throws SQLException;
    public boolean insert(T model) {
        try {
            DbConnection connection = new DbConnection();
            Statement statement  = connection.getStatement(getInsertQuery(), GeneratedKey.RETURN_GENERATED_KEYS);
            setInsertQueryParameters(statement, model);
            int rowsAffected = statement.executeUpdate();
            if(0 < rowsAffected) {
                QuerySet querySet = statement.getGeneratedKeys();
                if(querySet.next()) {
                    setPrimaryKey(querySet, model);
                }
                return true;
            }
        } catch (SQLException e) {
            return false;
        }
        return false;
    }

    protected abstract String getUpdateQuery();
    protected abstract void setUpdateQueryParameters(Statement statement, T model) throws SQLException;
    public boolean update(T model) {
        try {
            DbConnection connection = new DbConnection();
            Statement statement  = connection.getStatement(getUpdateQuery());
            setUpdateQueryParameters(statement, model);
            int rowsAffected = statement.executeUpdate();
            return 0 < rowsAffected;
        } catch (SQLException e) {
            return false;
        }
    }

    protected abstract String getDeleteQuery();
    protected abstract void setDeleteQueryParameters(Statement statement, T model) throws SQLException;
    public boolean delete(T model) {
        try {
            DbConnection connection = new DbConnection();
            Statement statement  = connection.getStatement(getDeleteQuery());
            setDeleteQueryParameters(statement, model);
            int rowsAffected = statement.executeUpdate();
            return 0 < rowsAffected;
        } catch (SQLException e) {
            return false;
        }
    }

}
