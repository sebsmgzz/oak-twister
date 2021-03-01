package database;

import database.representations.QuerySet;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Statement {

    private final PreparedStatement preparedStatement;

    public QuerySet getGeneratedKeys() throws SQLException {
        return new QuerySet(preparedStatement.getGeneratedKeys());
    }

    public Statement(PreparedStatement preparedStatement) {
        this.preparedStatement = preparedStatement;
    }

    public void set(int index, int value) throws SQLException {
        preparedStatement.setInt(index, value);
    }

    public void set(int index, String value) throws SQLException {
        preparedStatement.setString(index, value);
    }

    public void set(int index, Date value) throws SQLException {
        preparedStatement.setDate(index, value);
    }

    public void set(int index, Object object) throws SQLException {
        preparedStatement.setObject(index, object);
    }

    public boolean execute() throws SQLException {
        return preparedStatement.execute();
    }

    public int executeUpdate() throws SQLException {
        return preparedStatement.executeUpdate();
    }

    public QuerySet executeQuery() throws SQLException {
        return new QuerySet(preparedStatement.executeQuery());
    }

    public void finalize() {
        // TODO: fix obsolete destructor
        try {
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

