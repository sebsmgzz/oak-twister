package managers;

import database.DbConnection;
import database.Statement;
import database.representations.QueryResult;
import database.representations.QuerySet;
import managers.Manager;
import models.identity.Identity;
import models.identity.IdentitySerializer;

import java.sql.SQLException;

public class IdentityManager extends Manager<Identity> {

    public IdentityManager(IdentitySerializer serializer) {
        super(serializer);
    }

    @Override
    protected String getCreateTableQuery() {
        return "CREATE TABLE identities (" +
                "id        INTEGER  PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "firstName TEXT     NOT NULL," +
                "lastName  TEXT     NOT NULL," +
                "email     TEXT     NOT NULL " + ");";
    }

    @Override
    protected String getSelectAllQuery() {
        return "SELECT * FROM identities;";
    }

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO identities (firstName, lastName, email) VALUES (?, ?, ?);";
    }

    @Override
    protected void setInsertQueryParameters(Statement statement, Identity model) throws SQLException {
        statement.set(1, model.getFirstName());
        statement.set(2, model.getLastName());
        statement.set(3, model.getEmail());
    }

    @Override
    protected void setPrimaryKey(QueryResult queryResult, Identity model) throws SQLException {

        model.setId(queryResult.getInt("last_insert_rowid()"));
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE identities SET firstName = ?, lastName = ?, email = ? WHERE id = ?;";
    }

    @Override
    protected void setUpdateQueryParameters(Statement statement, Identity model) throws SQLException {
        statement.set(1, model.getFirstName());
        statement.set(2, model.getLastName());
        statement.set(3, model.getEmail());
        statement.set(4, model.getId());
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM identities WHERE id = ?;";
    }

    @Override
    protected void setDeleteQueryParameters(Statement statement, Identity model) throws SQLException {
        statement.set(1, model.getId());
    }

    public Identity select(int id) {
        try {
            DbConnection connection = new DbConnection();
            Statement statement = connection.getStatement("SELECT * FROM identities WHERE id = ?;");
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
