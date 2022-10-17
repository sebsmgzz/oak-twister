package com.oaktwister.services.json;

import org.json.JSONArray;

public interface JsonArraySerializer<T> {

    T deserialize(JSONArray jsonArray);

    JSONArray serialize(T entity);

}
