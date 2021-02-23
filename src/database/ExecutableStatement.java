package database;

import database.statements.BaseStatement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExecutableStatement {

    private final PreparedStatement preparedStatement;
    public final BaseStatement baseStatement;

    public ExecutableStatement(PreparedStatement preparedStatement, BaseStatement baseStatement) {
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

    public QueryResult executeQuery() throws SQLException {
        baseStatement.setParameters(preparedStatement);
        ResultSet resultSet = preparedStatement.executeQuery();
        return new QueryResult(resultSet);
    }

}
