package database;

import database.statements.CreateTable;
import database.statements.Select;
import database.statements.SelectAll;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private static DbConnection instance;

    public static DbConnection getInstance() throws SQLException {
        if(instance == null) {
            instance = new DbConnection();
        }
        return instance;
    }

    private final Connection connection;

    private DbConnection() throws SQLException {
        String path = System.getProperty("user.dir") + "/data/db.sqlite";
        this.connection = DriverManager.getConnection("jdbc:sqlite:" + path);
    }

    public SelectAll getSelectAllStatement() throws SQLException {
        return new SelectAll(connection.createStatement());
    }

    public Select getSelectStatement() throws SQLException {
        return new Select(connection.createStatement());
    }

    public CreateTable getCreateTableStatement() throws SQLException {
        return new CreateTable(connection.createStatement());
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
