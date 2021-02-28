package models;

import database.QueryResult;
import middleware.metadata.MetaModel;

import java.util.concurrent.Callable;

public abstract class Serializer<T extends Model> {

    private final MetaModel metaModel;
    private final Callable<T> factory;

    public Serializer(MetaModel metaModel, Callable<T> factory) {
        this.metaModel = metaModel;
        this.factory = factory;
    }

    public T serialize(QueryResult result) {
        try {
            T model = factory.call();
            // TODO
        } catch (Exception e) {
            return null;
        }
        return null;
    }

}
