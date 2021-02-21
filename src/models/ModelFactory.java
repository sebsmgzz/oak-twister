package models;

import models.HelloWorld;

public class ModelFactory {

    private HelloWorld helloWorld;

    public HelloWorld getHelloWorld() {
        if(helloWorld == null) {
            helloWorld = new HelloWorld();
        }
        return helloWorld;
    }

}
