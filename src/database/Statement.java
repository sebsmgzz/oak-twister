package database;

import database.entities.EntityTable;
import database.statements.BaseStatement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Statement {

    private final PreparedStatement preparedStatement;
    public final BaseStatement baseStatement;

    public Statement(PreparedStatement preparedStatement, BaseStatement baseStatement) {
        this.preparedStatement = preparedStatement;
        this.baseStatement = baseStatement;
    }

    public boolean execute() throws SQLException {
        baseStatement.setParameters(preparedStatement);
        return preparedStatement.execute();
    }

    public int executeUpdate() throws SQLException {
        baseStatement.setParameters(preparedStatement);
        return preparedStatement.executeUpdate();
    }

    public EntityTable executeQuery() throws SQLException {
        baseStatement.setParameters(preparedStatement);
        ResultSet resultSet = preparedStatement.executeQuery();
        return new EntityTable(resultSet);
    }

}
