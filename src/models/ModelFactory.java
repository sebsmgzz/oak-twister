package models;

import java.lang.reflect.InvocationTargetException;

public class ModelFactory {

    public Account getAccount() {
        return new Account();
    }

    public Identity getIdentity() {
        return new Identity();
    }

    public Password getPassword() {
        return new Password();
    }

    public Platform getPlatform() {
        return new Platform();
    }

    public <T extends BaseDataModel> T get(Class<T> type) {
        try {
            return type.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | InvocationTargetException |
                IllegalAccessException | NoSuchMethodException e) {
            return null;
        }
    }

}
