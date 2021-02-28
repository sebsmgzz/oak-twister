package models.platform;

import models.BaseManager;

public class PlatformManager extends BaseManager<Platform> {

    public PlatformManager(PlatformMetaModel metaModel, PlatformSerializer serializer) {
        super(metaModel, serializer);
    }

    public Platform selectById(int id) {
        return super.select("id", id);
    }

}
