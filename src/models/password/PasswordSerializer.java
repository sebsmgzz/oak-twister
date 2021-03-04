package models.password;

import database.representations.QueryResult;
import models.Serializer;
import models.account.Account;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

public class PasswordSerializer extends Serializer<Password> {

    @Override
    public Password serialize(QueryResult result) {
        Password password = new Password();
        try {
            password.setId(result.getInt("id"));
            password.setCreated(result.getDate("created"));
            password.setAccount(new Account());
            password.getAccount().setId(result.getInt("account"));
            password.setValue(result.getString("value"));
        } catch (SQLException e) {
            return null;
        }
        return password;
    }

    @Override
    public Password serialize(HashMap<String, String> map) {
        Password password = new Password();
        try {
            password.setId(Integer.parseInt(map.getOrDefault("id", null)));
            password.setCreated(new SimpleDateFormat("yyyy-MM-dd").parse(map.getOrDefault("created", null)));
            password.setAccount(new Account());
            password.getAccount().setId(Integer.parseInt(map.getOrDefault("account", null)));
            password.setValue(map.getOrDefault("value", null));
        } catch (Exception e) {
            return password;
        }
        return password;
    }

}
