package models;

import database.representations.QueryResult;

import java.util.Date;

public class Account extends Model {

    private int id;
    private Platform platform;
    private Identity identity;
    private Date created;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public Identity getIdentity() {
        return identity;
    }

    public void setIdentity(Identity identity) {
        this.identity = identity;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public void serialize(QueryResult result) {
        try {
            id = result.getInt("id");
            created = result.getDate("created");
            identity = new Identity();
            identity.setId(result.getInt("identity"));
            platform = new Platform();
            platform.setId(result.getInt("platform"));
        } catch (Exception ignored) { }
    }

    @Override
    public String toString() {
        return platform.toString() + "_" + identity.toString();
    }

}
