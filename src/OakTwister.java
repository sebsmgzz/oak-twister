import models.*;
import models.utils.ModelManager;
import views.ControllerFactory;
import viewmodels.ViewModelFactory;
import javafx.application.Application;
import javafx.stage.Stage;
import views.ViewHandler;

public class OakTwister extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        setupDatabase();
        ModelFactory modelFactory = new ModelFactory();
        ViewModelFactory viewModelFactory = new ViewModelFactory(modelFactory);
        ControllerFactory controllerFactory = new ControllerFactory(viewModelFactory);
        ViewHandler viewHandler = new ViewHandler(primaryStage, controllerFactory);
        viewHandler.start();
    }

    public void setupDatabase() {
        ManagerFactory managerFactory = new ManagerFactory(new MetaModelFactory());
        managerFactory.getAccountManager().createTable();
        managerFactory.getIdentityManager().createTable();
        managerFactory.getPasswordManager().createTable();
        managerFactory.getPlatformManager().createTable();
    }

}
