package com.oaktwister.infrastructure.serializers.grants;

import com.oaktwister.domain.models.grants.SecretGrant;
import org.json.JSONObject;

public class SecretGrantSerializer extends GrantSerializer<SecretGrant> {

    public final String HINT_KEY = "hint";

    @Override
    public SecretGrant deserialize(JSONObject json) {
        String name = json.getString(NAME_KEY);
        String value = json.getString(VALUE_KEY);
        String hint = json.getString(HINT_KEY);
        return new SecretGrant(name, value, hint);
    }

    @Override
    public JSONObject serialize(SecretGrant grant) {
        JSONObject json = new JSONObject();
        json.put(NAME_KEY, grant.getName());
        json.put(TYPE_KEY, SecretGrant.class.getTypeName());
        json.put(VALUE_KEY, grant.getValue());
        json.put(HINT_KEY, grant.getHint());
        return json;
    }
}
