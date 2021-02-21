import factories.ModelFactory;
import factories.ViewFactory;
import factories.ViewModelFactory;
import javafx.application.Application;
import javafx.stage.Stage;

public class OakTwister extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        ModelFactory modelFactory = new ModelFactory();
        ViewModelFactory viewModelFactory = new ViewModelFactory(modelFactory);
        ViewFactory viewFactory = new ViewFactory(primaryStage, viewModelFactory);
        viewFactory.start();
    }

}
