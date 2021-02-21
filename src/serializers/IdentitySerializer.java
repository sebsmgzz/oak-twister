package serializers;

import models.Identity;

import java.util.HashMap;
import java.util.Map;

public class IdentitySerializer implements Serializer<Identity> {

    @Override
    public Identity serialize(Map<String, Object> map) {
        Identity identity = new Identity();
        int id = (int) map.getOrDefault("id", -1);
        identity.setId(id);
        String firstName = (String) map.getOrDefault("first_name", null);
        identity.setFirstName(firstName);
        String lastName = (String) map.getOrDefault("last_name", null);
        identity.setLastName(lastName);
        String email = (String) map.getOrDefault("email", null);
        identity.setEmail(email);
        return identity;
    }

    @Override
    public Map<String, Object> deserialize(Identity identity) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", identity.getId());
        map.put("first_name", identity.getFirstName());
        map.put("last_name", identity.getLastName());
        map.put("email", identity.getEmail());
        return map;
    }

}
