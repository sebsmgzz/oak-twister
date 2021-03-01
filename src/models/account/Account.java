package models.account;

import models.Model;
import models.identity.Identity;
import models.platform.Platform;

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
    public String toString() {
        return platform.toString() + "_" + identity.toString();
    }

}
