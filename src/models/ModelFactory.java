package models;

import java.lang.reflect.InvocationTargetException;

public class ModelFactory {

    public HelloWorld getHelloWorld() {
        return new HelloWorld();
    }

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

    public <T extends BaseModel> T get(Class<T> type) {
        try {
            return type.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | InvocationTargetException |
                IllegalAccessException | NoSuchMethodException e) {
            return null;
        }
    }

}
