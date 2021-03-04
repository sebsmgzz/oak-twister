package models;

import database.representations.QueryResult;

import java.util.HashMap;

public abstract class Serializer<T extends Model> {

    public abstract T serialize(QueryResult result);

    public abstract T serialize(HashMap<String, String> map);

}
