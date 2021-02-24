package views;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewHandler {

    private final Stage stage;
    private final ViewFactory viewFactory;

    public ViewHandler(Stage stage, ViewFactory viewFactory) {
        this.stage = stage;
        this.viewFactory = viewFactory;
    }

    public void start() throws Exception {
        show(viewFactory.getHomeView());
    }

    public void show(Scene scene) {
        stage.setScene(scene);
        stage.show();
    }

}
