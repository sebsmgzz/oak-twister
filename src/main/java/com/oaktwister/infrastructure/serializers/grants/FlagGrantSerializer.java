package com.oaktwister.infrastructure.serializers.grants;

import com.oaktwister.domain.models.grants.FlagGrant;
import org.json.JSONObject;

public class FlagGrantSerializer extends GrantSerializer<FlagGrant> {

    @Override
    public FlagGrant deserialize(JSONObject json) {
        String name = json.getString(NAME_KEY);
        boolean value = json.getBoolean(VALUE_KEY);
        return new FlagGrant(name, value);
    }

    @Override
    public JSONObject serialize(FlagGrant grant) {
        JSONObject json = new JSONObject();
        json.put(NAME_KEY, grant.getName());
        json.put(TYPE_KEY, FlagGrant.class.getTypeName());
        json.put(VALUE_KEY, grant.getValue());
        return json;
    }
}
