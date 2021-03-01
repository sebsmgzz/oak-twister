package database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PrepStatement {

    PreparedStatement preparedStatement;

    public PrepStatement(PreparedStatement preparedStatement) {
        this.preparedStatement = preparedStatement;
    }

    public int executeUpdate() throws SQLException {
        return preparedStatement.executeUpdate();
    }

}

