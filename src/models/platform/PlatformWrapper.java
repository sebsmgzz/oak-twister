package models.platform;

import middleware.metadata.MetaModel;
import models.Wrapper;

public class PlatformWrapper extends Wrapper {

    private MetaModel metaModel;
    private PlatformSerializer serializer;
    private PlatformManager manager;

    @Override
    public Platform getData() {
        return new Platform();
    }

    @Override
    public MetaModel getMetaModel() {
        if(metaModel == null) {
            metaModel = new MetaModel(Platform.class);
        }
        return metaModel;
    }

    @Override
    public PlatformSerializer getSerializer() {
        if(serializer == null) {
            serializer = new PlatformSerializer(getMetaModel(), this::getData);
        }
        return serializer;
    }

    @Override
    public PlatformManager getManager() {
        if(manager == null) {
            manager = new PlatformManager(getMetaModel(), getSerializer());
        }
        return manager;
    }

}
