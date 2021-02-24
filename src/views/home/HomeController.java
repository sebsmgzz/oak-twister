package views.home;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.TilePane;
import viewmodels.HomeViewModel;
import views.BaseController;

public final class HomeController extends BaseController<HomeViewModel> {

    @FXML
    private TilePane tilePane;

    @Override
    public void initialize() {
        ObservableList<Node> children = tilePane.getChildren();
        //TODO: populate panes from factory
    }

}
