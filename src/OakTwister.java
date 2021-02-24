import models.*;
import models.utils.ModelManager;
import views.ViewFactory;
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
        ViewFactory viewFactory = new ViewFactory(viewModelFactory);
        ViewHandler viewHandler = new ViewHandler(primaryStage, viewFactory);
        viewHandler.start();
    }

    public void setupDatabase() {
        new ModelManager<>(Account.class).createTable();
        new ModelManager<>(Identity.class).createTable();
        new ModelManager<>(Password.class).createTable();
        new ModelManager<>(Platform.class).createTable();
    }

}
