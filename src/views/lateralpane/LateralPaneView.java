package views.lateralpane;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import viewmodels.LateralPaneViewModel;
import views.BaseView;
import views.Controller;

import java.io.IOException;

public final class LateralPaneView extends BaseView {

    public static final String SOURCE =  "/views/lateralpane/LateralPaneView.fxml";
    private final LateralPaneController controller;
    private final Parent node;

    @Override
    public Parent getNode() {
        return node;
    }

    @Override
    public Controller getController() {
        return controller;
    }

    public LateralPaneView(LateralPaneViewModel viewModel) throws IOException {
        FXMLLoader loader = super.getLoader(SOURCE);
        this.node = loader.load();
        this.controller = loader.getController();
        this.controller.setup(viewModel);
    }

}
