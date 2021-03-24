package models;

import database.representations.QueryResult;

public abstract class Model {

    public abstract void serialize(QueryResult result);

    @Override
    public abstract String toString();

}
