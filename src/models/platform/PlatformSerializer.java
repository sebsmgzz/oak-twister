package models.platform;

import middleware.metadata.MetaModel;
import models.Serializer;

import java.util.concurrent.Callable;

public class PlatformSerializer extends Serializer<Platform> {

    public PlatformSerializer(MetaModel metaModel, Callable<Platform> factory) {
        super(metaModel, factory);
    }

}
