package com.oaktwister.app.views.platforms;

import com.oaktwister.app.core.UIContext;
import com.oaktwister.app.utils.listeners.ListItemAddedListener;
import com.oaktwister.app.utils.listeners.ListItemRemovedListener;
import com.oaktwister.app.viewmodels.models.PlatformViewModel;
import com.oaktwister.app.viewmodels.views.PlatformsViewModel;
import com.oaktwister.app.views.Controller;
import com.oaktwister.app.views.widgets.dialogs.Alert;
import com.oaktwister.app.views.widgets.dialogs.AlertType;
import com.oaktwister.app.views.widgets.dialogs.DialogResult;
import com.oaktwister.app.views.widgets.crud.CrudFrame;
import com.oaktwister.app.views.widgets.crud.CrudPage;
import com.oaktwister.domain.models.claims.MetaGrant;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import java.util.Collection;
import java.util.HashMap;

public final class PlatformsController extends Controller<CrudFrame> {

    private final static String DELETE_CONFIRMATION_MESSAGE =
            "Are you sure you want to delete %s platform? \n" +
            "This action will delete all related accounts as well and cannot be undone.";
    private final static String NO_PLATFORM_SELECTED_MESSAGE = "Please select a platform before continuing.";

    private final UIContext ui;
    private final PlatformsViewModel viewModel;

    private final CrudFrame crudFrame;
    private final CrudPage<PlatformPane> crudPage;

    private final HashMap<PlatformViewModel, PlatformPane> platformMapping;
    private final ListItemAddedListener<PlatformViewModel> platformAddedListener;
    private final ListItemRemovedListener<PlatformViewModel> platformRemovedListener;

    public PlatformsController(UIContext ui) {
        this.ui = ui;
        viewModel = ui.viewModels().platforms();
        crudFrame = new CrudFrame();
        crudPage = new CrudPage<>();
        platformMapping = new HashMap<>();
        platformAddedListener = new ListItemAddedListener<>(this::onPlatformAdded);
        platformRemovedListener = new ListItemRemovedListener<>(this::onPlatformRemoved);
    }

    @Override
    protected CrudFrame initialize() {
        crudFrame.setContent(crudPage);
        crudPage.selectedPaneProperty().addListener((observable, oldValue, newValue) -> {
            PlatformViewModel platform = newValue.getGraphic().getPlatform();
            viewModel.setSelectedPlatform(platform);
        });
        crudFrame.onAddActionProperty().set(this::onAddPlatform);
        crudFrame.onEditActionProperty().set(this::onEditPlatform);
        crudFrame.onRemoveActionProperty().set(this::onRemovePlatform);
        viewModel.platformsProperty().addListener(platformAddedListener);
        viewModel.platformsProperty().addListener(platformRemovedListener);
        return crudFrame;
    }

    private void onAddPlatform(ActionEvent actionEvent) {
        EditPlatformDialog dialog = new EditPlatformDialog(ui);
        dialog.showAndWait();
        if(dialog.resultProperty().get() == DialogResult.SAVED) {
            // TODO: Save to database
            PlatformViewModel platform = dialog.getPlatform();
            System.out.println("Saving platform to database");
        }
    }

    private void onEditPlatform(ActionEvent actionEvent) {
        PlatformViewModel platform = viewModel.getSelectedPlatform();
        if(platform == null) {
            Alert alert = new Alert();
            alert.setAlertType(AlertType.INFO);
            alert.setMessage(NO_PLATFORM_SELECTED_MESSAGE);
            Stage stage = ui.navigation().getDialogStage(alert);
            alert.showAndWait(stage);
        } else {
            EditPlatformDialog dialog = new EditPlatformDialog(ui);
            dialog.getPlatform().copy(platform);
            dialog.showAndWait();
            if(dialog.resultProperty().get() == DialogResult.SAVED) {
                // TODO: Save to database
                System.out.println("Updating platform to database");
            }
        }
    }

    private void onRemovePlatform(ActionEvent actionEvent) {
        PlatformViewModel platform = viewModel.getSelectedPlatform();
        Alert alert = new Alert();
        alert.setAlertType(AlertType.CONFIRM);
        alert.setMessage(String.format(DELETE_CONFIRMATION_MESSAGE, platform.getId()));
        Stage stage = ui.navigation().getDialogStage(alert);
        alert.showAndWait(stage);
        DialogResult result = alert.resultProperty().get();
        if(result == DialogResult.YES) {
            boolean deleted = platform.delete();
            if(deleted) {
                viewModel.removePlatform(platform);
            }  else {
                // TODO: Alert the exception
                Exception error = platform.getError();
                error.printStackTrace();
                platform.clearError();
            }
        }
    }

    private void onPlatformAdded(PlatformViewModel platform) {
        PlatformPane platformPane = new PlatformPane();
        platformPane.setPlatform(platform);
        platformMapping.put(platform, platformPane);
        crudPage.add(platformPane);
    }

    private void onPlatformRemoved(PlatformViewModel platform) {
        PlatformPane platformPane = platformMapping.remove(platform);
        crudPage.remove(platformPane);
    }

    public void reloadPlatforms() {
        viewModel.clearPlatforms();
        viewModel.load();
    }

}
