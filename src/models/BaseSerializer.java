package models;

import java.util.HashMap;

public abstract class BaseSerializer<T extends BaseDataModel> {

    public abstract T serialize(HashMap<String, Object> map);

    public abstract HashMap<String, Object> deserialize(T model);

}
