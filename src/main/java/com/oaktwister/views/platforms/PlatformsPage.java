package com.oaktwister.views.platforms;

import com.oaktwister.annotations.ViewDescriptor;
import com.oaktwister.core.UIContext;
import com.oaktwister.events.PlatformPaneEvent;
import com.oaktwister.services.resources.ViewResources;
import com.oaktwister.utils.extensions.MapUtil;
import com.oaktwister.utils.extensions.NodeUtil;
import com.oaktwister.utils.listeners.ListItemAddedListener;
import com.oaktwister.utils.listeners.ListItemRemovedListener;
import com.oaktwister.viewmodels.collections.PlatformsViewModel;
import com.oaktwister.viewmodels.models.PlatformViewModel;
import com.oaktwister.views.DialogResult;
import com.oaktwister.views.widgets.CrudPage;
import com.oaktwister.views.widgets.FlowPage;

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
        NodeUtil.loadControl(this);
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
