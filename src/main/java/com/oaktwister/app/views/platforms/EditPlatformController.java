package com.oaktwister.app.views.platforms;

import com.oaktwister.app.core.UIContext;
import com.oaktwister.app.services.resources.ImageResources;
import com.oaktwister.app.viewmodels.models.PlatformViewModel;
import com.oaktwister.app.views.Controller;
import com.oaktwister.app.views.widgets.dialogs.DialogResult;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public final class EditPlatformController extends Controller<EditPlatformDialog> {

    // Context
    private final UIContext ui;

    // UI
    private final EditPlatformDialog dialog;

    // Other
    private Stage stage;

    public EditPlatformController(UIContext ui) {
        this.ui = ui;
        dialog = new EditPlatformDialog();
    }

    @Override
    protected EditPlatformDialog initialize() {
        dialog.setPlatform(ui.viewModels().platform());
        dialog.resultProperty().addListener((observableValue, oldValue, newValue) -> {
            if(stage != null) {
                stage.close();
            }
        });
        return dialog;
    }

    public DialogResult showAndWait() {
        stage = ui.stages().getDialogStage(getRoot());
        Image logo = new Image(ImageResources.FontAwesome.LAYER_GROUP_SOLID);
        stage.getIcons().add(logo);
        stage.showAndWait();
        return dialog.getResult();
    }

    public PlatformViewModel getPlatform() {
        return getRoot().getPlatform();
    }

}
