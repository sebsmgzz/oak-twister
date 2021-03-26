import management.Manager;
import viewmodels.ViewModelFactory;
import views.ControllerFactory;
import views.ViewHandler;
import javafx.application.Application;
import javafx.stage.Stage;

public class OakTwister extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Manager manager = Manager.getInstance();
        ViewModelFactory viewModelFactory = new ViewModelFactory(manager);
        ControllerFactory controllerFactory = new ControllerFactory(viewModelFactory);
        ViewHandler viewHandler = new ViewHandler(primaryStage, controllerFactory);
        viewHandler.start();
    }

}
