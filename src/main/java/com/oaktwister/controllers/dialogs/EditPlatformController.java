package com.oaktwister.controllers.dialogs;

import com.oaktwister.viewmodels.models.PlatformViewModel;
import com.oaktwister.views.DialogResult;
import com.oaktwister.views.platforms.EditPlatformDialog;

import javafx.stage.Stage;

public class EditPlatformController {

    private final Stage stage;

    private final EditPlatformDialog view;

    public EditPlatformController(Stage stage) {
        this.stage = stage;
        view = new EditPlatformDialog();
    }

    public EditPlatformDialog getView() {
        return view;
    }

    public void setPlatform(PlatformViewModel viewModel) {
        view.nameProperty().bindBidirectional(viewModel.nameProperty());
        view.urlProperty().bindBidirectional(viewModel.urlProperty());
        view.imageProperty().bindBidirectional(viewModel.imageProperty());
        view.claimsProperty().bind(viewModel.claimMap().claimsProperty());
        view.resultProperty().addListener((observable, oldValue, newValue) -> stage.close());
    }

    public DialogResult getResult() {
        return view.resultProperty().get();
    }

    public void showAndWait() {
        stage.showAndWait();
    }

}
