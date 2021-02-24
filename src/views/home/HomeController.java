package views.home;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.TilePane;
import viewmodels.HomeViewModel;

public class HomeController {

    @FXML
    private TilePane tilePane;

    private HomeViewModel viewModel;

    public void init(HomeViewModel viewModel) {
        this.viewModel = viewModel;
        ObservableList<Node> children = tilePane.getChildren();
    }

}
