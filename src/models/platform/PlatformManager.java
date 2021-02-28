package models.platform;

import middleware.metadata.MetaModel;
import models.Manager;

public class PlatformManager extends Manager<Platform> {

    public PlatformManager(MetaModel metaModel, PlatformSerializer serializer) {
        super(metaModel, serializer);
    }

    public Platform selectById(int id) {
        return selectWhere("id", id);
    }

}
