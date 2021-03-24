package managers;

import database.DbConnection;
import database.Statement;
import database.representations.QueryResult;
import database.representations.QuerySet;
import models.Platform;
import models.platform.PlatformSerializer;

import java.sql.SQLException;

public class PlatformManager extends Manager<Platform> {

    public PlatformManager(PlatformSerializer serializer) {
        super(serializer);
    }

    @Override
    protected String getCreateTableQuery() {
        return "CREATE TABLE platforms (" +
                "id    INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "name  TEXT    NOT NULL," +
                "image BLOB    NOT NULL " + ");";
    }

    @Override
    protected String getSelectAllQuery() {
        return "SELECT * FROM platforms;";
    }

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO platforms (name, image) VALUES (?, ?);";
    }

    @Override
    protected void setInsertQueryParameters(Statement statement, Platform model) throws SQLException {
        statement.set(1, model.getName());
        statement.set(2, model.getImage());
    }

    @Override
    protected void setPrimaryKey(QueryResult queryResult, Platform model) throws SQLException {
        model.setId(queryResult.getInt("last_insert_rowid()"));
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE platforms SET name = ?, image = ? WHERE id = ?;";
    }

    @Override
    protected void setUpdateQueryParameters(Statement statement, Platform model) throws SQLException {
        statement.set(1, model.getName());
        statement.set(2, model.getImage());
        statement.set(3, model.getId());
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM platforms WHERE id = ?;";
    }

    @Override
    protected void setDeleteQueryParameters(Statement statement, Platform model) throws SQLException {
        statement.set(1, model.getId());
    }

    public Platform select(int id) {
        try {
            DbConnection connection = new DbConnection();
            Statement statement = connection.getStatement("SELECT * FROM platforms WHERE id = ?;");
            statement.set(1, id);
            QuerySet querySet = statement.executeQuery();
            if(querySet.next()) {
                return serializer.serialize(querySet);
            }
        } catch (SQLException e) {
            return null;
        }
        return null;
    }

}
