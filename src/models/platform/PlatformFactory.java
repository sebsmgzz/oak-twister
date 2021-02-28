package models.platform;

public class PlatformFactory {

    private PlatformMetaModel metaModel;
    private PlatformSerializer serializer;
    private PlatformManager manager;

    public Platform getDataModel() {
        return new Platform();
    }

    public PlatformMetaModel getMetaModel() {
        if(metaModel == null) {
            metaModel = new PlatformMetaModel();
        }
        return metaModel;
    }

    public PlatformSerializer getSerializer() {
        if(serializer == null) {
            serializer = new PlatformSerializer();
        }
        return serializer;
    }

    public PlatformManager getManager() {
        if(manager == null) {
            manager = new PlatformManager(getMetaModel(), getSerializer());
        }
        return manager;
    }
    
}
