package database;

import database.querys.BaseQuery;

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

    public Statement getStatement(BaseQuery baseQuery) throws SQLException {
        String query = baseQuery.getQuery();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        return new Statement(preparedStatement, baseQuery);
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
