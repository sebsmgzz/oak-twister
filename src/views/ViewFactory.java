package views;

import javafx.scene.Node;
import viewmodels.HomeViewModel;
import viewmodels.LateralPaneViewModel;
import viewmodels.PaneViewModel;
import viewmodels.ViewModelFactory;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.net.URL;

public class ViewFactory {

    private final ViewModelFactory viewModelFactory;
    private Parent homeView;
    private Parent lateralPane;

    public Parent getHomeView() throws IOException {
        if(homeView == null) {
            FXMLLoader loader = getLoader("Home");
            Parent root = loader.load();
            BaseController<HomeViewModel> controller = loader.getController();
            controller.setup(this, viewModelFactory.getHomeViewModel());
            homeView = root;
        }
        return homeView;
    }

    public Parent getLateralPaneView() throws IOException {
        if(lateralPane == null) {
            FXMLLoader loader = getLoader("LateralPane");
            Parent root = loader.load();
            BaseController<LateralPaneViewModel> controller = loader.getController();
            controller.setup(this, viewModelFactory.getLateralPaneViewModel());
            lateralPane = root;
        }
        return lateralPane;
    }

    public Node getPaneView() throws IOException {
        FXMLLoader loader = getLoader("Pane");
        Parent root = loader.load();
        BaseController<PaneViewModel> controller = loader.getController();
        controller.setup(this, viewModelFactory.getPaneViewModel());
        return root;
    }

    public ViewFactory(ViewModelFactory viewModelFactory) {
        this.viewModelFactory = viewModelFactory;
    }

    private FXMLLoader getLoader(String name) {
        FXMLLoader loader = new FXMLLoader();
        String path = "/views/" + name.toLowerCase() + "/" + name + "View.fxml";
        URL url = getClass().getResource(path);
        loader.setLocation(url);
        return loader;
    }

}
