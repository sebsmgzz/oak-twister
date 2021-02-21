package managers;

import database.clauses.Where;
import database.statements.Select;
import metamodels.MetaModel;
import models.BaseModel;
import database.DbConnection;
import database.QueryResult;
import database.statements.SelectAll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BaseManager {

    private final BaseModel model;

    public static BaseModel test() { return null; }

    public BaseManager(BaseModel model) {
        this.model = model;
    }

    public List<BaseModel> selectAll() {
        List<BaseModel> models = new ArrayList<BaseModel>();
        try {
            SelectAll statement = DbConnection.getInstance().getSelectAllStatement();
            MetaModel meta = model.getModelMeta();
            statement.setTableName(meta.table.name());
            QueryResult result = statement.executeQuery();
            while(result.next()) {
                BaseModel instance = model.getNew();
                HashMap<String, Object> results = result.getAllValues();
                instance.getSerializer().serialize(results);
                models.add(instance);
            }
        } catch (SQLException e) {
            return null;
        }
        return models;
    }

    public BaseModel select(Where where) {
        try {
            Select statement = DbConnection.getInstance().getSelectStatement();
            MetaModel meta = model.getModelMeta();
            statement.setTableName(meta.table.name());
            statement.setWhere(where);
            QueryResult result = statement.executeQuery();
            if(result.next()) {
                BaseModel instance = model.getNew();
                HashMap<String, Object> results = result.getAllValues();
                instance.getSerializer().serialize(results);
                return instance;
            }
            return null;
        } catch (SQLException e) {
            return null;
        }
    }

    public boolean save() {
        // TODO: create / update statements
        return false;
    }

    public boolean delete() {
        // TODO: delete statement
        return false;
    }

}
