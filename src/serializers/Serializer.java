package serializers;

import models.BaseModel;

import java.util.Map;

public interface Serializer<T extends BaseModel> {

    T serialize(Map<String, Object> map);

    Map<String, Object> deserialize(T model);

}
