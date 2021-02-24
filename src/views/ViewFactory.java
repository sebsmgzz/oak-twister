package views;

import viewmodels.ViewModelFactory;
import views.controllers.HomeController;

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
        openView("Home");
    }

    public void openView(String viewName) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        String path = "/views/markup/" + viewName + "View.fxml";
        URL url = getClass().getResource(path);
        loader.setLocation(url);
        Parent root = loader.load();
        if("Home".equals(viewName)) {
            HomeController view = loader.getController();
            view.init(viewModelFactory.getHomeViewModel());
            stage.setTitle("HelloWorld");
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
