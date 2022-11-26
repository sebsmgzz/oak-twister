package com.oaktwister.infrastructure.serializers.grants;

import com.oaktwister.domain.models.grants.NumberGrant;
import org.json.JSONObject;

public class NumberGrantSerializer extends GrantSerializer<NumberGrant> {

    @Override
    public NumberGrant deserialize(JSONObject json) {
        String name = json.getString(NAME_KEY);
        Number value = json.getNumber(VALUE_KEY);
        return new NumberGrant(name, value);
    }

    @Override
    public JSONObject serialize(NumberGrant grant) {
        JSONObject json = new JSONObject();
        json.put(NAME_KEY, grant.getName());
        json.put(TYPE_KEY, NumberGrant.class.getTypeName());
        json.put(VALUE_KEY, grant.getValue());
        return json;
    }

}
