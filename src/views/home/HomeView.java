package views.home;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import viewmodels.HomeViewModel;
import views.BaseView;
import views.Controller;

import java.io.IOException;

public final class HomeView extends BaseView {

    public static final String SOURCE =  "/views/home/HomeView.fxml";
    private final HomeController controller;
    private final Parent node;

    @Override
    public Parent getNode() {
        return node;
    }

    @Override
    public Controller getController() {
        return controller;
    }

    public HomeView(HomeViewModel viewModel) throws IOException {
        FXMLLoader loader = super.getLoader(SOURCE);
        this.node = loader.load();
        this.controller = loader.getController();
        this.controller.setup(viewModel);
    }

}
