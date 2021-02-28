package models;

import middleware.metadata.MetaModel;

public abstract class Wrapper {

    public abstract Model getData();

    public abstract MetaModel getMetaModel();

    public abstract Serializer<?> getSerializer();

    public abstract Manager<?> getManager();

}
