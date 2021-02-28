package models.password;

import middleware.annotations.Column;
import middleware.annotations.ForeignKey;
import middleware.annotations.Table;
import database.entities.DataType;
import models.Model;
import models.account.Account;

import java.util.Date;

@Table(name = "passwords")
public class Password extends Model {

    @Column(name = "id", type = DataType.INTEGER, primaryKey = true)
    public int id;

    @Column(name = "account", type = DataType.INTEGER)
    @ForeignKey(model = Account.class)
    public Account account;

    @Column(name = "created", type = DataType.TEXT)
    public Date created;

    @Column(name = "value", type = DataType.TEXT)
    public String value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

}
