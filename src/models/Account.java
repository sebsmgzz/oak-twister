package models;

import annotations.Column;
import annotations.ForeignKey;
import annotations.Table;
import database.metaentities.Type;
import models.BaseDataModel;
import models.Identity;
import models.Platform;

import java.util.Date;

@Table(name = "accounts")
public class Account extends BaseDataModel {

    @Column(name = "id", type = Type.INTEGER, primaryKey = true)
    private int id;

    @Column(name = "platform", type = Type.INTEGER)
    @ForeignKey(model = Platform.class)
    private Platform platform;

    @Column(name = "identity", type = Type.INTEGER)
    @ForeignKey(model = Identity.class)
    private Identity identity;

    @Column(name = "created", type = Type.TEXT)
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
