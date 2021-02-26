package database.statements;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface BaseStatement {

    String getQuery();

    default void setParameters(PreparedStatement statement) throws SQLException { };

}
