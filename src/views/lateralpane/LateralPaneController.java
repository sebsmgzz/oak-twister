package views.lateralpane;

import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import viewmodels.LateralPaneViewModel;
import views.BaseController;

public final class LateralPaneController extends BaseController<LateralPaneViewModel> {

    @FXML
    private Hyperlink identities;

    @FXML
    private Hyperlink accounts;

    @FXML
    private Hyperlink passwords;

    @Override
    public void init() { }

}
