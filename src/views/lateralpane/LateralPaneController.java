package views.lateralpane;

import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import viewmodels.LateralPaneViewModel;
import views.BaseController;

import java.net.URL;
import java.util.ResourceBundle;

public final class LateralPaneController implements BaseController {

    private LateralPaneViewModel viewModel;

    @FXML
    private Hyperlink identities;
    @FXML
    private Hyperlink accounts;
    @FXML
    private Hyperlink passwords;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.viewModel = null; // TODO: link this
    }

}
