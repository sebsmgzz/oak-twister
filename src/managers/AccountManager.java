package managers;

import database.DbConnection;
import database.Statement;
import database.representations.QueryResult;
import database.representations.QuerySet;
import managers.Manager;
import models.account.Account;
import models.account.AccountSerializer;

import java.sql.SQLException;

public class AccountManager extends Manager<Account> {

    public AccountManager(AccountSerializer serializer) {
        super(serializer);
    }

    @Override
    protected String getCreateTableQuery() {
        return "CREATE TABLE accounts (" +
                "id       INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "platform INTEGER NOT NULL," +
                "identity INTEGER NOT NULL," +
                "created  TEXT    NOT NULL," +
                "FOREIGN KEY(platform) REFERENCES platforms(id)," +
                "FOREIGN KEY(identity) REFERENCES identities(id)" + ");";
    }

    @Override
    protected String getSelectAllQuery() {
        return "SELECT * FROM accounts;";
    }

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO accounts (platform, identity, created) VALUES (?, ?, ?);";
    }

    @Override
    protected void setInsertQueryParameters(Statement statement, Account model) throws SQLException {
        statement.set(1, model.getPlatform().getId());
        statement.set(2, model.getIdentity().getId());
        statement.set(3, model.getCreated());
    }

    @Override
    protected void setPrimaryKey(QueryResult queryResult, Account model) throws SQLException {
        model.setId(queryResult.getInt("last_insert_rowid()"));
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE accounts SET platform = ?, identity = ?, created = ? WHERE id = ?;";
    }

    @Override
    protected void setUpdateQueryParameters(Statement statement, Account model) throws SQLException {
        statement.set(1, model.getPlatform().getId());
        statement.set(2, model.getIdentity().getId());
        statement.set(3, model.getCreated());
        statement.set(4, model.getId());
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM accounts WHERE id = ?;";
    }

    @Override
    protected void setDeleteQueryParameters(Statement statement, Account model) throws SQLException {
        statement.set(1, model.getId());
    }

    public Account select(int id) {
        try {
            DbConnection connection = new DbConnection();
            Statement statement = connection.getStatement("SELECT * FROM accounts WHERE id = ?;");
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
