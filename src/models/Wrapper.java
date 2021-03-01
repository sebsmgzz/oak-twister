package models;

public abstract class Wrapper {

    public abstract Model getData();

    public abstract Serializer<?> getSerializer();

    public abstract Manager<?> getManager();

}
