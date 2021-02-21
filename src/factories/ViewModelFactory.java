package app.factories;

import app.viewmodels.HelloWorldViewModel;

public class ViewModelFactory {

    private ModelFactory modelFactory;
    private HelloWorldViewModel helloWorldViewModel;

    public HelloWorldViewModel getHelloWorldViewModel() {
        if(helloWorldViewModel == null) {
            helloWorldViewModel = new HelloWorldViewModel(modelFactory.getHelloWorld());
        }
        return helloWorldViewModel;
    }

    public ViewModelFactory(ModelFactory modelFactory) {
        this.modelFactory = modelFactory;
    }

}
