package managers;

import database.DbConnection;
import database.QueryResult;
import database.Statement;
import database.statements.BaseStatement;
import database.statements.SelectFrom;
import database.statements.SelectFromWhere;
import metamodels.MetaModel;
import models.Identity;
import serializers.IdentitySerializer;

import java.util.ArrayList;
import java.util.List;

public class IdentityManager {

    private final MetaModel metaModel;
    private final IdentitySerializer serializer;

    public IdentityManager(MetaModel metaModel, IdentitySerializer identitySerializer) {
        this.metaModel = metaModel;
        this.serializer = identitySerializer;
    }

    public List<Identity> selectAll() {
        List<Identity> models = new ArrayList<>();
        try {
            DbConnection connection = new DbConnection();
            BaseStatement baseStatement = new SelectFrom(metaModel.getTableName());
            Statement statement = connection.getStatement(baseStatement);
            QueryResult result = statement.executeQuery();
            while(result.next()) {
                Identity model = serializer.serialize(result.getAllValues());
                models.add(model);
            }
        } catch (Exception e) {
            return null;
        }
        return models;
    }

    public Identity select(int id) {
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
