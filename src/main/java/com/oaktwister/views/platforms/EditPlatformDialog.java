package com.oaktwister.views.platforms;

import com.oaktwister.viewmodels.models.PlatformViewModel;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogEvent;

import java.util.Optional;
import java.util.function.Consumer;

public class EditPlatformDialog {

    private final EditPlatformDialogPane dialogPane;
    private final PlatformViewModel viewModel;

    private Consumer<PlatformViewModel> onFinish;

    public EditPlatformDialog(EditPlatformDialogPane dialogPane, PlatformViewModel viewModel) {
        this.dialogPane = dialogPane;
        this.viewModel = viewModel;
    }

    public Consumer<PlatformViewModel> getOnFinish() {
        return onFinish;
    }

    public void setOnFinish(Consumer<PlatformViewModel> onFinish) {
        this.onFinish = onFinish;
    }

    public void showAndWait() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(dialogPane);
        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && onFinish != null) {
            onFinish.accept(viewModel);
        }
    }

}
