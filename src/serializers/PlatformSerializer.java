package serializers;

import models.Platform;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class PlatformSerializer extends BaseSerializer {

    private final Callable<Platform> modelFactory;

    public PlatformSerializer(Callable<Platform> modelFactory) {
        this.modelFactory = modelFactory;
    }

    public Platform serialize(Map<String, Object> map) throws Exception {
        Platform platform = modelFactory.call();
        int id = (int) map.getOrDefault("id", -1);
        platform.setId(id);
        String name = (String) map.getOrDefault("name", null);
        platform.setName(name);
        Image image = (Image) map.getOrDefault("image", null);
        platform.setImage(image);
        return platform;
    }

    public Map<String, Object> deserialize(Platform platform) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", platform.getId());
        map.put("name", platform.getName());
        map.put("image", platform.getImage());
        return map;
    }

}
