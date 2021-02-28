package models;

import middleware.annotations.Table;

@Table(name = "")
public abstract class Model {

    @Override
    public abstract String toString();

}
