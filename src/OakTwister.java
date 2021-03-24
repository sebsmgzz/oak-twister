import viewmodels.ViewModelFactory;
import views.ControllerFactory;
import views.ViewHandler;
import javafx.application.Application;
import javafx.stage.Stage;

public class OakTwister extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        WrapperFactory wrapperFactory = new WrapperFactory();
        setupDatabase(wrapperFactory);
        ViewModelFactory viewModelFactory = new ViewModelFactory(wrapperFactory);
        ControllerFactory controllerFactory = new ControllerFactory(viewModelFactory);
        ViewHandler viewHandler = new ViewHandler(primaryStage, controllerFactory);
        viewHandler.start();
    }

    public void setupDatabase(WrapperFactory wrapperFactory) {
        wrapperFactory.getIdentity().getManager().createTable();
        wrapperFactory.getPlatform().getManager().createTable();
        wrapperFactory.getPassword().getManager().createTable();
        wrapperFactory.getAccount().getManager().createTable();
    }

}
