package models;

import managers.Manager;

public abstract class Wrapper {

    public abstract Model getData();

    public abstract Serializer<?> getSerializer();

    public abstract Manager<?> getManager();

}
