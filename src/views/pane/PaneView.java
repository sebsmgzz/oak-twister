package views.pane;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import viewmodels.PaneViewModel;
import views.BaseView;
import views.Controller;

import java.io.IOException;

public final class PaneView extends BaseView {

    public static final String SOURCE =  "/views/pane/PaneView.fxml";
    private final PaneController controller;
    private final Parent node;

    @Override
    public Parent getNode() {
        return node;
    }

    @Override
    public Controller getController() {
        return controller;
    }

    public PaneView(PaneViewModel viewModel) throws IOException {
        FXMLLoader loader = super.getLoader(SOURCE);
        this.node = loader.load();
        this.controller = loader.getController();
        this.controller.setup(viewModel);
    }

}
