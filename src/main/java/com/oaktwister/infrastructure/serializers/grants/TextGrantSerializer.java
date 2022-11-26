package com.oaktwister.infrastructure.serializers.grants;

import com.oaktwister.domain.models.grants.TextGrant;
import org.json.JSONObject;

public class TextGrantSerializer extends GrantSerializer<TextGrant> {

    @Override
    public TextGrant deserialize(JSONObject json) {
        String name = json.getString(NAME_KEY);
        String value = json.getString(VALUE_KEY);
        return new TextGrant(name, value);
    }

    @Override
    public JSONObject serialize(TextGrant grant) {
        JSONObject json = new JSONObject();
        json.put(NAME_KEY, grant.getName());
        json.put(TYPE_KEY, TextGrant.class.getTypeName());
        json.put(VALUE_KEY, grant.getValue());
        return json;
    }
}
