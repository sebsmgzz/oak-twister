package database;

import database.entities.EntityTable;
import database.querys.BaseQuery;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Statement {

    private final PreparedStatement preparedStatement;
    public final BaseQuery baseQuery;

    public Statement(PreparedStatement preparedStatement, BaseQuery baseQuery) {
        this.preparedStatement = preparedStatement;
        this.baseQuery = baseQuery;
    }

    public boolean execute() throws SQLException {
        baseQuery.setParameters(preparedStatement);
        return preparedStatement.execute();
    }

    public int executeUpdate() throws SQLException {
        baseQuery.setParameters(preparedStatement);
        return preparedStatement.executeUpdate();
    }

    public EntityTable executeQuery() throws SQLException {
        baseQuery.setParameters(preparedStatement);
        ResultSet resultSet = preparedStatement.executeQuery();
        return new EntityTable(resultSet);
    }

}
