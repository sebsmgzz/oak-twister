package database;

import database.entities.Entity;
import database.commands.BaseCommand;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Statement {

    private final PreparedStatement preparedStatement;
    public final BaseCommand baseCommand;

    public Statement(PreparedStatement preparedStatement, BaseCommand baseCommand) {
        this.preparedStatement = preparedStatement;
        this.baseCommand = baseCommand;
    }

    public boolean execute() throws SQLException {
        baseCommand.setParameters(preparedStatement);
        return preparedStatement.execute();
    }

    public int executeUpdate() throws SQLException {
        baseCommand.setParameters(preparedStatement);
        return preparedStatement.executeUpdate();
    }

    public Entity executeQuery() throws SQLException {
        baseCommand.setParameters(preparedStatement);
        ResultSet resultSet = preparedStatement.executeQuery();
        return new Entity(resultSet);
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
