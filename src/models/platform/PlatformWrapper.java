package models.platform;

import models.Wrapper;

public class PlatformWrapper extends Wrapper {

    private PlatformSerializer serializer;
    private PlatformManager manager;

    @Override
    public Platform getData() {
        return new Platform();
    }

    @Override
    public PlatformSerializer getSerializer() {
        if(serializer == null) {
            serializer = new PlatformSerializer();
        }
        return serializer;
    }

    @Override
    public PlatformManager getManager() {
        if(manager == null) {
            manager = new PlatformManager(getSerializer());
        }
        return manager;
    }

}
