package database.commands;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface BaseCommand {

    String getQuery();

    default void setParameters(PreparedStatement statement) throws SQLException { };

}
