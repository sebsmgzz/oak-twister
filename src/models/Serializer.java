package models;

import database.QueryResult;

public abstract class Serializer<T extends Model> {

    public abstract T serialize(QueryResult result);

}
