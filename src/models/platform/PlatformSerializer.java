package models.platform;

import database.QueryResult;
import models.Serializer;

import java.sql.SQLException;

public class PlatformSerializer extends Serializer<Platform> {

    @Override
    public Platform serialize(QueryResult result) {
        try {
            Platform platform = new Platform();
            platform.setId(result.getInt("id"));
            platform.setImage(result.getImage("image"));
            return platform;
        } catch (SQLException e) {
            return null;
        }
    }

}
