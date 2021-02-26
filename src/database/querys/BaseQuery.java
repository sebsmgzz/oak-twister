package database.querys;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface BaseQuery {

    String getQuery();

    default void setParameters(PreparedStatement statement) throws SQLException { };

}
