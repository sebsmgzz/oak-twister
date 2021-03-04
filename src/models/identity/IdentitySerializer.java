package models.identity;

import database.representations.QueryResult;
import models.Serializer;

import java.sql.SQLException;
import java.util.HashMap;

public class IdentitySerializer extends Serializer<Identity> {

    @Override
    public Identity serialize(QueryResult result) {
        Identity identity = new Identity();
        try {
            identity.setId(result.getInt("id"));
            identity.setEmail(result.getString("email"));
            identity.setFirstName(result.getString("firstName"));
            identity.setLastName(result.getString("lastName"));
        } catch (SQLException e) {
            return identity;
        }
        return identity;
    }

    @Override
    public Identity serialize(HashMap<String, String> map) {
        Identity identity = new Identity();
        try {
            identity.setId(Integer.parseInt(map.getOrDefault("id", "-1")));
            identity.setEmail(map.getOrDefault("email", null));
            identity.setFirstName(map.getOrDefault("firstName", null));
            identity.setLastName(map.getOrDefault("lastName", null));
        } catch (Exception e) {
            return identity;
        }
        return identity;
    }

}
