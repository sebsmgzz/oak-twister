package models.identity;

import database.representations.QueryResult;
import models.Serializer;

import java.sql.SQLException;

public class IdentitySerializer extends Serializer<Identity> {

    @Override
    public Identity serialize(QueryResult result) {
        try {
            Identity identity = new Identity();
            identity.setId(result.getInt("id"));
            identity.setEmail(result.getString("email"));
            identity.setFirstName(result.getString("firstName"));
            identity.setLastName(result.getString("lastName"));
            return identity;
        } catch (SQLException e) {
            return null;
        }
    }

}
