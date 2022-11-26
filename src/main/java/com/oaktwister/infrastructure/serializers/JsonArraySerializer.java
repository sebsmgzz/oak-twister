package com.oaktwister.infrastructure.serializers;

import org.json.JSONArray;

public interface JsonArraySerializer<T> {

    T deserialize(JSONArray jsonArray);

    JSONArray serialize(T entity);

}
