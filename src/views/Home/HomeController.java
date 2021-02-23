package views.Home;

import viewmodels.HomeViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HomeController {

    @FXML
    private Label output;

    @FXML
    private TextField input;

    private HomeViewModel viewModel;

    public void init(HomeViewModel viewModel) {
        this.viewModel = viewModel;
        output.textProperty().bindBidirectional(viewModel.getOutput());
        input.textProperty().bindBidirectional(viewModel.getInput());
    }

    public void onUpdate(ActionEvent actionEvent) {
        viewModel.update();
    }

}
