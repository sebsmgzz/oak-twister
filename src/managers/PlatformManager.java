package managers;

import database.DbConnection;
import database.QueryResult;
import database.Statement;
import database.statements.BaseStatement;
import database.statements.SelectFrom;
import database.statements.SelectFromWhere;
import models.Platform;
import serializers.PlatformSerializer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlatformManager {

    private static final String TABLE_NAME = "Platforms";
    private final PlatformSerializer serializer = new PlatformSerializer();

    public List<Platform> selectAll() {
        List<Platform> models = new ArrayList<>();
        try {
            DbConnection connection = new DbConnection();
            BaseStatement baseStatement = new SelectFrom(TABLE_NAME);
            Statement statement = connection.getStatement(baseStatement);
            QueryResult result = statement.executeQuery();
            while(result.next()) {
                Platform model = serializer.serialize(result.getAllValues());
                models.add(model);
            }
        } catch (SQLException e) {
            return null;
        }
        return models;
    }

    public Platform select(int id) {
        try {
            DbConnection connection = new DbConnection();
            BaseStatement baseStatement = new SelectFromWhere(TABLE_NAME, "id", id);
            Statement statement = connection.getStatement(baseStatement);
            QueryResult result = statement.executeQuery();
            if(result.next()) {
                return serializer.serialize(result.getAllValues());
            }
        } catch (SQLException e) {
            return null;
        }
        return null;
    }
    
}
