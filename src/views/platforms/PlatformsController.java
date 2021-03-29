package views.platforms;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.FlowPane;
import viewmodels.PlatformsViewModel;
import views.Controller;

import java.net.URL;
import java.util.ResourceBundle;

public class PlatformsController extends Controller implements Initializable {

    private final PlatformsViewModel viewModel;

    @FXML
    public FlowPane flowPane;

    public PlatformsController(PlatformsViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Node> children = flowPane.getChildren();
//        for(int i = 0; i < 5; i++) {
//            Node paneView = factory.getPaneController().getNode();
//            children.add(paneView);
//        }
    }

}
