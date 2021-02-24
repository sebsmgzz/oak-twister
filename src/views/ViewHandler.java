package views;

import javafx.scene.Parent;
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
        Parent root = viewFactory.getHomeView();
        show(new Scene(root));
    }

    public void show(Scene scene) {
        stage.setScene(scene);
        stage.show();
    }

}
