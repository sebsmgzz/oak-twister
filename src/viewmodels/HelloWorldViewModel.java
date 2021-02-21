package app.viewmodels;

import app.models.HelloWorld;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class HelloWorldViewModel {

    private StringProperty output;
    private StringProperty input;

    private HelloWorld helloWorld;

    public StringProperty getOutput() {
        return output;
    }

    public StringProperty getInput() {
        return input;
    }

    public HelloWorldViewModel(HelloWorld model) {
        this.helloWorld = model;
        this.output = new SimpleStringProperty();
        this.input = new SimpleStringProperty();
    }

    public void update() {
        output.setValue(input.getValue());
    }

}
