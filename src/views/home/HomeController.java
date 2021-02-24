package views.home;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.TilePane;
import viewmodels.HomeViewModel;
import views.BaseController;

import java.io.IOException;

public final class HomeController extends BaseController<HomeViewModel> {

    @FXML
    private TilePane tilePane;

    @Override
    public void init() {
        ObservableList<Node> children = tilePane.getChildren();
        try {
            for(int i = 0; i < 5; i++) {
                Node paneView = factory.getPaneView();
                children.add(paneView);
            }
        } catch (IOException ignored) { }
    }

}
