package database.statements;

import database.QueryResult;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public abstract class BaseStatement {

    private final Statement statement;

    public BaseStatement(Statement statement) {
        this.statement = statement;
    }

    public abstract String getQuery();

    public int executeUpdate() throws SQLException {
        return statement.executeUpdate(getQuery());
    }

    public QueryResult executeQuery() throws SQLException {
        ResultSet resultSet = statement.executeQuery(getQuery());
        return new QueryResult(resultSet);
    }

}
