import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import management.Manager;
import viewmodels.ViewModelFactory;
import views.ControllerFactory;
import views.ViewLoader;
import views.home.HomeController;

public class OakTwister extends Application {

    private final Manager manager;
    private final ViewModelFactory viewModelFactory;
    private final ControllerFactory controllerFactory;
    private final ViewLoader viewLoader;

    public OakTwister() {
        this.manager = new Manager();
        this.viewModelFactory = new ViewModelFactory(manager);
        this.controllerFactory = new ControllerFactory(viewModelFactory);
        this.viewLoader = new ViewLoader(controllerFactory);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = viewLoader.load(HomeController.VIEW_SOURCE);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

}
