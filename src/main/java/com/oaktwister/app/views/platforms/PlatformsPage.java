package com.oaktwister.app.views.platforms;

import com.oaktwister.app.annotations.ViewDescriptor;
import com.oaktwister.app.core.UIContext;
import com.oaktwister.app.events.PlatformPaneEvent;
import com.oaktwister.app.services.resources.ViewResources;
import com.oaktwister.app.utils.extensions.MapUtil;
import com.oaktwister.app.utils.extensions.FXMLUtil;
import com.oaktwister.app.utils.listeners.ListItemAddedListener;
import com.oaktwister.app.utils.listeners.ListItemRemovedListener;
import com.oaktwister.app.viewmodels.views.PlatformsViewModel;
import com.oaktwister.app.viewmodels.models.PlatformViewModel;
import com.oaktwister.app.views.DialogResult;
import com.oaktwister.app.views.widgets.CrudPage;
import com.oaktwister.app.views.widgets.FlowPage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.HashMap;

@ViewDescriptor(location = ViewResources.Platforms.PAGE)
public class PlatformsPage extends AnchorPane {

    private final UIContext ui;
    private final PlatformsViewModel viewModel;

    @FXML private CrudPage crudPage;
    @FXML private FlowPage<PlatformPane> flowPage;

    private final HashMap<PlatformViewModel, PlatformPane> platformsMap;
    private final ListItemAddedListener<PlatformViewModel> platformViewModelAddedListener;
    private final ListItemRemovedListener<PlatformViewModel> platformViewModelRemovedListener;

    public PlatformsPage(UIContext ui) {
        this.ui = ui;
        viewModel = ui.viewModels().platforms();
        platformsMap = new HashMap<>();
        platformViewModelAddedListener = new ListItemAddedListener<>(this::onPlatformViewModelAdded);
        platformViewModelRemovedListener = new ListItemRemovedListener<>(this::onPlatformViewModelRemoved);
        FXMLUtil.loadControl(PlatformsPage.class, this);
    }

    public void initialize() {
        crudPage.onAddActionProperty().set(this::onAddPlatformPane);
        viewModel.platformsProperty().addListener(platformViewModelAddedListener);
        viewModel.platformsProperty().addListener(platformViewModelRemovedListener);
    }

    public void reloadPlatforms() {
        viewModel.clear();
        viewModel.load();
    }

    private void onAddPlatformPane(ActionEvent actionEvent) {
        EditPlatformDialog dialog = new EditPlatformDialog();
        PlatformViewModel platformViewModel = ui.viewModels().platform();
        dialog.platformProperty().set(platformViewModel);
        Stage stage = ui.navigation().getDialogStage(dialog);
        dialog.stageProperty().set(stage);
        if(dialog.resultProperty().get() == DialogResult.SAVED) {
            // TODO: Save to database
            System.out.println("Saving platform to database");
        }
    }

    private void onPlatformViewModelAdded(PlatformViewModel platformViewModel) {
        PlatformPane platformPane = new PlatformPane();
        platformPane.onMainActionProperty().set(this::onPlatformPaneClick);
        platformPane.onDeleteActionProperty().set(this::onPlatformPaneDeleteClick);
        platformPane.identifierProperty().bind(platformViewModel.idProperty());
        platformPane.imageProperty().bind(platformViewModel.imageProperty());
        platformPane.nameProperty().bind(platformViewModel.nameProperty());
        platformPane.createdAtProperty().bind(platformViewModel.createdAtProperty());
        flowPage.panesProperty().add(platformPane);
        platformsMap.put(platformViewModel, platformPane);
    }

    private void onPlatformViewModelRemoved(PlatformViewModel platformViewModel) {
        PlatformPane platformPane = platformsMap.remove(platformViewModel);
        flowPage.panesProperty().remove(platformPane);
    }

    private void onPlatformPaneClick(PlatformPaneEvent actionEvent) {
        PlatformPane platformPane = actionEvent.getPlatformPane();
        PlatformViewModel platformViewModel = MapUtil.getKeyByValue(platformsMap, platformPane);
        if(platformViewModel == null) {
            // TODO: Throw exception? This should never happen
            return;
        }
        EditPlatformDialog dialog = new EditPlatformDialog();
        dialog.platformProperty().set(platformViewModel);
        ui.navigation().getDialogStage(dialog);
        if(dialog.resultProperty().get() == DialogResult.SAVED) {
            // TODO: Save to database
            System.out.println("Updating platform to database");
        }
    }

    private void onPlatformPaneDeleteClick(PlatformPaneEvent actionEvent) {
        // TODO: Show DeletePlatformAlert
    }

}
