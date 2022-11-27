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

        // Display the dialog to add the platform
        EditPlatformDialog dialog = new EditPlatformDialog(ui);
        dialog.showAndWait();

        // If the user did not save the dialog, simply end execution
        if(dialog.getResult() != DialogResult.SAVED) {
            return;
        }

        // Create the platform
        PlatformViewModel platform = dialog.getPlatform();
        boolean inserted = platform.insert();
        if(inserted) {
            // Update the UI
            viewModel.addPlatform(platform);
        } else {
            // TODO: Show an alert of the failed operation
            Exception error = platform.getError();
            error.printStackTrace();
            platform.clearError();
        }

    }

    private void onEditPlatform(ActionEvent actionEvent) {

        // If not selectedPlatform is selected, show an alert
        PlatformViewModel selectedPlatform = viewModel.getSelectedPlatform();
        if(selectedPlatform == null) {
            Alert alert = new Alert();
            alert.setAlertType(AlertType.INFO);
            alert.setMessage(NO_PLATFORM_SELECTED_MESSAGE);
            Stage stage = ui.stages().getDialogStage(alert);
            alert.showAndWait(stage);
            return;
        }

        // Display the dialog to edit the selectedPlatform
        EditPlatformDialog dialog = new EditPlatformDialog(ui);
        PlatformViewModel platform = dialog.getPlatform();
        platform.copy(selectedPlatform);
        dialog.showAndWait();

        // If the user did not save the dialog, simply end execution
        if(dialog.resultProperty().get() != DialogResult.SAVED) {
            return;
        }

        // Update the selectedPlatform
        boolean updated = platform.update();
        if(updated) {
            // Update the UI
            selectedPlatform.copy(dialog.getPlatform());
        } else {
            // TODO: Show an alert of the failed operation
            Exception error = platform.getError();
            error.printStackTrace();
            platform.clearError();
        }

    }

    private void onRemovePlatform(ActionEvent actionEvent) {

        // Display the alert to confirm the deletion
        PlatformViewModel platform = viewModel.getSelectedPlatform();
        Alert alert = new Alert();
        alert.setAlertType(AlertType.CONFIRM);
        alert.setMessage(String.format(DELETE_CONFIRMATION_MESSAGE, platform.getId()));
        Stage stage = ui.stages().getDialogStage(alert);
        alert.showAndWait(stage);

        // If user didn't agreed to delete, simply end execution
        if(alert.getResult() != DialogResult.YES) {
            return;
        }

        // Delete the platform
        boolean deleted = platform.delete();
        if(deleted) {
            // Update the UI
            viewModel.removePlatform(platform);
        } else {
            // TODO: Show an alert of the failed operation
            Exception error = platform.getError();
            error.printStackTrace();
            platform.clearError();
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
