package views.home;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.TilePane;
import viewmodels.HomeViewModel;
import views.BaseController;

import java.net.URL;
import java.util.ResourceBundle;

public final class HomeController implements BaseController {

    private HomeViewModel viewModel;

    @FXML
    private TilePane tilePane;

    public void initialize(HomeViewModel viewModel) {
        this.viewModel = viewModel;
        ObservableList<Node> children = tilePane.getChildren();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.viewModel = null; // TODO: link this
        ObservableList<Node> children = tilePane.getChildren();
    }

}
