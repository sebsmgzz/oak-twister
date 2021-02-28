package models.password;

import middleware.metadata.MetaModel;
import models.Serializer;

import java.util.concurrent.Callable;

public class PasswordSerializer extends Serializer<Password> {

    public PasswordSerializer(MetaModel metaModel, Callable<Password> factory) {
        super(metaModel, factory);
    }

}
