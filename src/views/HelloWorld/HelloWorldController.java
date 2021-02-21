package views.HelloWorld;

import viewmodels.HelloWorldViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloWorldController {

    @FXML
    private Label output;

    @FXML
    private TextField input;

    private HelloWorldViewModel viewModel;

    public void init(HelloWorldViewModel viewModel) {
        this.viewModel = viewModel;
        output.textProperty().bindBidirectional(viewModel.getOutput());
        input.textProperty().bindBidirectional(viewModel.getInput());
    }

    public void onUpdate(ActionEvent actionEvent) {
        viewModel.update();
    }

}
