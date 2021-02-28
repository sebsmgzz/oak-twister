package database;

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
        Entity entity = new Entity();
        entity.populate(resultSet);
        return entity;
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
