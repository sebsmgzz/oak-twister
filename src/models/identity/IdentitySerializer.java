package models.identity;

import database.QueryResult;
import models.Serializer;

import java.sql.SQLException;

public class IdentitySerializer extends Serializer<Identity> {

    @Override
    public Identity serialize(QueryResult result) {
        try {
            Identity identity = new Identity();
            identity.setId(result.getInt("id"));
            identity.setEmail(result.getString("email"));
            identity.setFirstName(result.getString("first_name"));
            identity.setLastName(result.getString("last_name"));
            return identity;
        } catch (SQLException e) {
            return null;
        }
    }

}
