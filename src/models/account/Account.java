package models.account;

import annotations.Column;
import annotations.ForeignKey;
import annotations.Table;
import database.entities.DataType;
import models.BaseDataModel;
import models.identity.Identity;
import models.platform.Platform;

import java.util.Date;

@Table(name = "accounts")
public class Account extends BaseDataModel {

    @Column(name = "id", type = DataType.INTEGER, primaryKey = true)
    private int id;

    @Column(name = "platform", type = DataType.INTEGER)
    @ForeignKey(model = Platform.class)
    private Platform platform;

    @Column(name = "identity", type = DataType.INTEGER)
    @ForeignKey(model = Identity.class)
    private Identity identity;

    @Column(name = "created", type = DataType.TEXT)
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
