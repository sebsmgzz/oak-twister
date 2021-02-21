package app.factories;

import app.views.HelloWorld.HelloWorldController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;

public class ViewFactory {

    private final Stage stage;
    private final ViewModelFactory viewModelFactory;

    public ViewFactory(Stage stage, ViewModelFactory viewModelFactory) {
        this.stage = stage;
        this.viewModelFactory = viewModelFactory;
    }

    public void start() throws Exception {
        openView("HelloWorld");
    }

    public void openView(String viewName) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        String path = "/app/views/" + viewName + "/" + viewName + "View.fxml";
        URL url = getClass().getResource(path);
        loader.setLocation(url);
        Parent root = loader.load();
        if("HelloWorld".equals(viewName)) {
            HelloWorldController view = loader.getController();
            view.init(viewModelFactory.getHelloWorldViewModel());
            stage.setTitle("HelloWorld");
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
