package views.home;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.TilePane;
import viewmodels.HomeViewModel;
import views.BaseController;

public final class HomeController implements BaseController {

    @FXML
    private TilePane tilePane;

    private HomeViewModel viewModel;

    public void initialize(HomeViewModel viewModel) {
        this.viewModel = viewModel;
        ObservableList<Node> children = tilePane.getChildren();
    }

}
