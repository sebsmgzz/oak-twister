package database.statements;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface BaseStatement {

    String getQuery();

    void setParameters(PreparedStatement statement) throws SQLException;

}
