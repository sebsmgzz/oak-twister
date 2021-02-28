package models.identity;

import middleware.metadata.MetaModel;
import models.Serializer;

import java.util.concurrent.Callable;

public class IdentitySerializer extends Serializer<Identity> {

    public IdentitySerializer(MetaModel metaModel, Callable<Identity> factory) {
        super(metaModel, factory);
    }

}
