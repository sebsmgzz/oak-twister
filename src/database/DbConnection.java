package database;

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

    public Statement getStatement(String query) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        return new Statement(statement);
    }

    public Statement getStatement(String query, GeneratedKey generatedKeys) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query, generatedKeys.getValue());
        return new Statement(statement);
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
