package managers;

import database.DbConnection;
import database.QueryResult;
import database.Statement;
import database.statements.BaseStatement;
import database.statements.SelectFrom;
import database.statements.SelectFromWhere;
import models.Password;
import serializers.PasswordSerializer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PasswordManager {

    private final PasswordSerializer serializer;

    public PasswordManager(PasswordSerializer serializer) {
        this.serializer = serializer;
    }

    public List<Password> selectAll() {
        List<Password> models = new ArrayList<>();
        try {
            DbConnection connection = new DbConnection();
            //TODO: call metamodel from factory
            BaseStatement baseStatement = new SelectFrom("passwords");
            Statement statement = connection.getStatement(baseStatement);
            QueryResult result = statement.executeQuery();
            while(result.next()) {
                Password model = serializer.serialize(result.getAllValues());
                models.add(model);
            }
        } catch (Exception e) {
            return null;
        }
        return models;
    }

    public Password select(int id) {
        try {
            DbConnection connection = new DbConnection();
            //TODO: call metamodel from factory
            BaseStatement baseStatement = new SelectFromWhere("passwords", "id", id);
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
