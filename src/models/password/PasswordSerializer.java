package models.password;

import database.representations.QueryResult;
import models.Serializer;
import models.account.Account;

import java.sql.SQLException;

public class PasswordSerializer extends Serializer<Password> {

    @Override
    public Password serialize(QueryResult result) {
        try {
            Password password = new Password();
            password.setId(result.getInt("id"));
            password.setCreated(result.getDate("created"));
            password.setAccount(new Account());
            password.getAccount().setId(result.getInt("account"));
            password.setValue(result.getString("value"));
            return password;
        } catch (SQLException e) {
            return null;
        }
    }

}
