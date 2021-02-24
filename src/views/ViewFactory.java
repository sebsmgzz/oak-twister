package views;

import javafx.scene.Node;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import viewmodels.*;

import java.io.IOException;
import java.net.URL;

public class ViewFactory {

    private final ViewModelFactory viewModelFactory;
    private Parent homeView;
    private Parent lateralPane;

    public Parent getHomeView() throws IOException {
        if(homeView == null) {
            FXMLLoader loader = getLoader("Home");
            homeView = load(loader, viewModelFactory.getHomeViewModel());;
        }
        return homeView;
    }

    public Parent getLateralPaneView() throws IOException {
        if(lateralPane == null) {
            FXMLLoader loader = getLoader("LateralPane");
            lateralPane = load(loader, viewModelFactory.getLateralPaneViewModel());
        }
        return lateralPane;
    }

    public Node getPaneView() throws IOException {
        FXMLLoader loader = getLoader("Pane");
        return load(loader, viewModelFactory.getPaneViewModel());
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

    private <T extends BaseViewModel> Parent load(FXMLLoader loader, T viewModel) throws IOException {
        Parent root = loader.load();
        BaseController<T> controller = loader.getController();
        controller.setup(this, viewModel);
        return root;
    }

}
