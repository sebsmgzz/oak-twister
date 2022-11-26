package com.oaktwister.infrastructure.serializers.grants;

import com.oaktwister.domain.models.grants.Grant;
import com.oaktwister.infrastructure.serializers.JsonObjectSerializer;
import org.json.JSONObject;

public abstract class GrantSerializer<T extends Grant<?>> implements JsonObjectSerializer<T> {

    public final static String NAME_KEY = "name";
    public final static String TYPE_KEY = "type";
    public final static String VALUE_KEY = "value";

    @Override
    public abstract T deserialize(JSONObject json);

    @Override
    public abstract JSONObject serialize(T grant);

}

