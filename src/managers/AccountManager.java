package managers;

import database.DbConnection;
import database.QueryResult;
import database.Statement;
import database.statements.BaseStatement;
import database.statements.SelectFrom;
import database.statements.SelectFromWhere;
import metamodels.MetaModel;
import models.Account;
import serializers.AccountSerializer;

import java.util.ArrayList;
import java.util.List;

public class AccountManager extends BaseManager {

    private final MetaModel metaModel;
    private final AccountSerializer serializer;

    public AccountManager(MetaModel metaModel, AccountSerializer serializer) {
        this.metaModel = metaModel;
        this.serializer = serializer;
    }

    public List<Account> selectAll() {
        List<Account> models = new ArrayList<>();
        try {
            DbConnection connection = new DbConnection();
            BaseStatement baseStatement = new SelectFrom(metaModel.getTableName());
            Statement statement = connection.getStatement(baseStatement);
            QueryResult result = statement.executeQuery();
            while(result.next()) {
                Account model = serializer.serialize(result.getAllValues());
                models.add(model);
            }
        } catch (Exception e) {
            return null;
        }
        return models;
    }

    public Account select(int id) {
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
