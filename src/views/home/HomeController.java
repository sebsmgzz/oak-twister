package views.home;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.TilePane;
import viewmodels.HomeViewModel;
import views.Controller;

public final class HomeController implements Controller {

    @FXML
    private TilePane tilePane;

    private HomeViewModel viewModel;

    public void setup(HomeViewModel viewModel) {
        this.viewModel = viewModel;
        ObservableList<Node> children = tilePane.getChildren();
        for(int i = 0; i < 5; i++) {
            // Node paneView = factory.getPaneView();
            // children.add(paneView);
        }
    }

}
