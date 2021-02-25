package views.home;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.TilePane;
import viewmodels.HomeViewModel;
import views.Controller;
import views.ControllerFactory;

import java.io.IOException;

public final class HomeController extends Controller {

    public static final String SOURCE =  "/views/home/HomeView.fxml";
    private Parent node;
    private HomeViewModel viewModel;

    @Override
    public Parent getNode() {
        return node;
    }

    @FXML
    private TilePane tilePane;

    public void setup(Parent node, HomeViewModel viewModel) {
        this.node = node;
        this.viewModel = viewModel;
    }

    public void populate(ControllerFactory factory) throws IOException {
        ObservableList<Node> children = tilePane.getChildren();
        for(int i = 0; i < 5; i++) {
            Node paneView = factory.getPaneController().getNode();
            children.add(paneView);
        }
    }

}
