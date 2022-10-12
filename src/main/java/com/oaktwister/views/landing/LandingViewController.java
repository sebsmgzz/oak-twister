package com.oaktwister.views.landing;

import com.oaktwister.core.ViewFactory;
import com.oaktwister.viewmodels.landing.LandingViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LandingViewController implements Initializable {

    private final ViewFactory viewFactory;
    private final LandingViewModel viewModel;

    @FXML
    private Label label;

    public LandingViewController(ViewFactory viewFactory, LandingViewModel viewModel) {
        this.viewFactory = viewFactory;
        this.viewModel = viewModel;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        label.textProperty().bindBidirectional(viewModel.getMessage());
    }

    @FXML
    public void onGreetButton(ActionEvent actionEvent) {
        viewModel.greet();
    }

    @FXML
    public void onGoToMainButton(ActionEvent actionEvent) throws IOException {
        viewFactory.showMainView();
    }

}
