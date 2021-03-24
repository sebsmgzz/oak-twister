package managers;

import database.DbConnection;
import database.Statement;
import database.representations.QueryResult;
import database.representations.QuerySet;
import managers.Manager;
import models.password.Password;
import models.password.PasswordSerializer;

import java.sql.SQLException;

public class PasswordManager extends Manager<Password> {

    public PasswordManager(PasswordSerializer serializer) {
        super(serializer);
    }

    @Override
    protected String getCreateTableQuery() {
        return "CREATE TABLE passwords (" +
                "id        INTEGER  PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "account   INTEGER  NOT NULL," +
                "created   TEXT     NOT NULL," +
                "value     TEXT     NOT NULL," +
                "FOREIGN KEY(account) REFERENCES accounts(id)" + ");";
    }

    @Override
    protected String getSelectAllQuery() {
        return "SELECT * FROM passwords;";
    }

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO passwords (account, created, value) VALUES (?, ?, ?);";
    }

    @Override
    protected void setInsertQueryParameters(Statement statement, Password model) throws SQLException {
        statement.set(1, model.getAccount().getId());
        statement.set(2, model.getCreated());
        statement.set(3, model.getValue());
    }

    @Override
    protected void setPrimaryKey(QueryResult queryResult, Password model) throws SQLException {
        model.setId(queryResult.getInt("last_insert_rowid()"));
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE passwords SET account = ?, created = ?, value = ? WHERE id = ?;";
    }

    @Override
    protected void setUpdateQueryParameters(Statement statement, Password model) throws SQLException {
        statement.set(1, model.getAccount().getId());
        statement.set(2, model.getCreated());
        statement.set(3, model.getValue());
        statement.set(4, model.getId());
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM passwords WHERE id = ?;";
    }

    @Override
    protected void setDeleteQueryParameters(Statement statement, Password model) throws SQLException {
        statement.set(1, model.getId());
    }

    public Password select(int id) {
        try {
            DbConnection connection = new DbConnection();
            Statement statement = connection.getStatement("SELECT * FROM passwords WHERE id = ?;");
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
