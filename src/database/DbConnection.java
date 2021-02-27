package database;

import database.commands.BaseCommand;

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

    public Statement getStatement(BaseCommand baseCommand) throws SQLException {
        String query = baseCommand.getQuery();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        return new Statement(preparedStatement, baseCommand);
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
