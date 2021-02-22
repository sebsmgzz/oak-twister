package models;

import annotations.Column;
import annotations.ForeignKey;
import annotations.Table;
import database.Type;

import java.util.Date;

@Table(name = "passwords")
public class Password extends BaseModel {

    @Column(name = "id", type = Type.INTEGER, primaryKey = true)
    public int id;

    @Column(name = "account", type = Type.INTEGER)
    @ForeignKey(model = Account.class)
    public Account account;

    @Column(name = "created", type = Type.TEXT)
    public Date created;

    @Column(name = "value", type = Type.TEXT)
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
