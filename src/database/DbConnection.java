package database;

import database.statements.BaseStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbConnection {

    private final Connection connection;

    public DbConnection() throws SQLException {
        String path = System.getProperty("user.dir") + "/data/db.sqlite";
        this.connection = DriverManager.getConnection("jdbc:sqlite:" + path);
    }

    public ExecutableStatement getStatement(BaseStatement baseStatement) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(baseStatement.getQuery());
        return new ExecutableStatement(preparedStatement, baseStatement);
    }

    public void finalize() {
        // TODO: fix obsolete destructor
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
