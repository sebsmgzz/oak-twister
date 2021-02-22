package managers;

import database.DbConnection;
import database.QueryResult;
import database.Statement;
import database.statements.BaseStatement;
import database.statements.SelectFrom;
import database.statements.SelectFromWhere;
import metamodels.MetaModel;
import models.Platform;
import serializers.PlatformSerializer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlatformManager {

    private final MetaModel metaModel;
    private final PlatformSerializer serializer;

    public PlatformManager(MetaModel metaModel, PlatformSerializer serializer) {
        this.metaModel = metaModel;
        this.serializer = serializer;
    }

    public List<Platform> selectAll() {
        List<Platform> models = new ArrayList<>();
        try {
            DbConnection connection = new DbConnection();
            BaseStatement baseStatement = new SelectFrom(metaModel.getTableName());
            Statement statement = connection.getStatement(baseStatement);
            QueryResult result = statement.executeQuery();
            while(result.next()) {
                Platform model = serializer.serialize(result.getAllValues());
                models.add(model);
            }
        } catch (Exception e) {
            return null;
        }
        return models;
    }

    public Platform select(int id) {
        try {
            DbConnection connection = new DbConnection();
            BaseStatement baseStatement = new SelectFromWhere(metaModel.getTableName(), "id", id);
            Statement statement = connection.getStatement(baseStatement);
            QueryResult result = statement.executeQuery();
            if(result.next()) {
                return serializer.serialize(result.getAllValues());
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }
    
}
