package views;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewHandler {

    private final Stage stage;
    private final ControllerFactory controllerFactory;

    public ViewHandler(Stage stage, ControllerFactory controllerFactory) {
        this.stage = stage;
        this.controllerFactory = controllerFactory;
    }

    public void start() throws Exception {
        Controller controller = controllerFactory.getHomeController();
        show(new Scene(controller.getNode()));
    }

    public void show(Scene scene) {
        stage.setScene(scene);
        stage.show();
    }

}
