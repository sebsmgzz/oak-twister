package views.lateralpane;

import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import viewmodels.LateralPaneViewModel;
import views.Controller;

public class LateralPaneController extends Controller {

    private final LateralPaneViewModel viewModel;

    public LateralPaneController(LateralPaneViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @FXML
    private Hyperlink platforms;

    @FXML
    private Hyperlink accounts;

}
