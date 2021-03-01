package models.password;

import models.Wrapper;

public class PasswordWrapper extends Wrapper {

    private PasswordSerializer serializer;
    private PasswordManager manager;

    @Override
    public Password getData() {
        return new Password();
    }

    @Override
    public PasswordSerializer getSerializer() {
        if(serializer == null) {
            serializer = new PasswordSerializer();
        }
        return serializer;
    }

    @Override
    public PasswordManager getManager() {
        if(manager == null) {
            manager = new PasswordManager(getSerializer());
        }
        return manager;
    }

}
