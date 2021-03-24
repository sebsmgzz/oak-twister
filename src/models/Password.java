package models;

import database.representations.QueryResult;

import java.sql.SQLException;
import java.util.Date;

public class Password extends Model {

    public int id;
    public Account account;
    public Date created;
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
    public void serialize(QueryResult result) {
        try {
            id = result.getInt("id");
            created = result.getDate("created");
            account = new Account();
            account.setId(result.getInt("account"));
            value = result.getString("value");
        } catch (SQLException ignored) { }
    }

    @Override
    public String toString() {
        return value;
    }

}
