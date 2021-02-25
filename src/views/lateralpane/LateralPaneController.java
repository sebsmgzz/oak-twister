package views.lateralpane;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Hyperlink;
import viewmodels.LateralPaneViewModel;
import views.Controller;

public final class LateralPaneController extends Controller {

    public static final String SOURCE = "/views/lateralpane/LateralPaneView.fxml";
    private Parent node;
    private LateralPaneViewModel viewModel;

    @FXML
    private Hyperlink identities;

    @FXML
    private Hyperlink accounts;

    @FXML
    private Hyperlink passwords;

    @Override
    public Parent getNode() {
        return node;
    }

    public void setup(Parent node, LateralPaneViewModel viewModel) {
        this.node = node;
        this.viewModel = viewModel;
    }

}
