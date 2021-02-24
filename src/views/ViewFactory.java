package views;

import viewmodels.ViewModelFactory;
import views.controllers.HomeController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.net.URL;

public class ViewFactory {

    private final ViewModelFactory viewModelFactory;

    private Scene homeView;

    public ViewFactory(ViewModelFactory viewModelFactory) {
        this.viewModelFactory = viewModelFactory;
    }

    private FXMLLoader getLoader(String name) {
        FXMLLoader loader = new FXMLLoader();
        String path = "/views/markup/" + name + "View.fxml";
        URL url = getClass().getResource(path);
        loader.setLocation(url);
        return loader;
    }

    public Scene getHomeView() throws IOException {
        if(homeView == null) {
            FXMLLoader loader = getLoader("Home");
            Parent root = loader.load();
            HomeController controller = loader.getController();
            controller.init(viewModelFactory.getHomeViewModel());
            homeView = new Scene(root);
        }
        return homeView;
    }

}
