package views.home;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.FlowPane;
import viewmodels.HomeViewModel;
import views.Controller;

public class HomeController extends Controller {

    public final static String VIEW_SOURCE = "/views/home/HomeView.fxml";
    private final HomeViewModel viewModel;

    @FXML
    private Parent platformView;

    @FXML
    private FlowPane flowPane;

    public HomeController(HomeViewModel viewModel) {
        this.viewModel = viewModel;
    }

}
