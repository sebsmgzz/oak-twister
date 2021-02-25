package views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import viewmodels.*;
import views.home.HomeController;
import views.lateralpane.LateralPaneController;
import views.pane.PaneController;

import java.io.IOException;
import java.net.URL;

public class ControllerFactory {

    private final ViewModelFactory viewModelFactory;
    private HomeController homeController;
    private LateralPaneController lateralPaneController;

    public HomeController getHomeController() throws IOException {
        if(homeController == null) {
            FXMLLoader loader = getLoader(HomeController.SOURCE);
            Parent node = loader.load();
            homeController = loader.getController();
            homeController.setup(node, viewModelFactory.getHomeViewModel());
            homeController.populate(this);
        }
        return homeController;
    }

    public LateralPaneController getLateralPaneController() throws IOException {
        if(lateralPaneController == null) {
            FXMLLoader loader = getLoader(LateralPaneController.SOURCE);
            Parent node = loader.load();
            lateralPaneController = loader.getController();
            lateralPaneController.setup(node, viewModelFactory.getLateralPaneViewModel());
        }
        return lateralPaneController;
    }

    public PaneController getPaneController() throws IOException {
        FXMLLoader loader = getLoader(PaneController.SOURCE);
        Parent node = loader.load();
        PaneController paneController = loader.getController();
        paneController.setup(node, viewModelFactory.getPaneViewModel());
        return paneController;
    }

    public ControllerFactory(ViewModelFactory viewModelFactory) {
        this.viewModelFactory = viewModelFactory;
    }

    private FXMLLoader getLoader(String resourcePath) {
        FXMLLoader loader = new FXMLLoader();
        URL url = getClass().getResource(resourcePath);
        loader.setLocation(url);
        return loader;
    }

}
