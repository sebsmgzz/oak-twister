import models.*;
import views.ControllerFactory;
import viewmodels.ViewModelFactory;
import javafx.application.Application;
import javafx.stage.Stage;
import views.ViewHandler;

public class OakTwister extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        DataModelFactory dataModelFactory = new DataModelFactory();
        MetaModelFactory metaModelFactory = new MetaModelFactory();
        SerializerFactory serializerFactory = new SerializerFactory();
        ManagerFactory managerFactory = new ManagerFactory(metaModelFactory, serializerFactory);
        setupDatabase(managerFactory);
        ViewModelFactory viewModelFactory = new ViewModelFactory(dataModelFactory);
        ControllerFactory controllerFactory = new ControllerFactory(viewModelFactory);
        ViewHandler viewHandler = new ViewHandler(primaryStage, controllerFactory);
        viewHandler.start();
    }

    public void setupDatabase(ManagerFactory managerFactory) {
        managerFactory.getAccountManager().createTable();
        managerFactory.getIdentityManager().createTable();
        managerFactory.getPasswordManager().createTable();
        managerFactory.getPlatformManager().createTable();
    }

}
