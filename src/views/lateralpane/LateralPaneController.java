package views.lateralpane;

import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import viewmodels.LateralPaneViewModel;
import views.Controller;

public final class LateralPaneController implements Controller {

    @FXML
    private Hyperlink identities;

    @FXML
    private Hyperlink accounts;

    @FXML
    private Hyperlink passwords;

    private LateralPaneViewModel viewModel;

    public void setup(LateralPaneViewModel viewModel) {
        this.viewModel = viewModel;
    }

}
