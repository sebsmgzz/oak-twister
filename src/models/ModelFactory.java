package models;

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

}
