package com.oaktwister.controllers.controls;

import com.oaktwister.controllers.dialogs.EditPlatformController;
import com.oaktwister.core.UIContext;
import com.oaktwister.services.resources.StringResources;
import com.oaktwister.utils.extensions.MapUtil;
import com.oaktwister.utils.listeners.ListItemAddedListener;
import com.oaktwister.utils.listeners.ListItemRemovedListener;
import com.oaktwister.viewmodels.collections.PlatformsViewModel;
import com.oaktwister.viewmodels.models.PlatformViewModel;
import com.oaktwister.views.widgets.FlowPage;
import com.oaktwister.views.widgets.PagePane;
import com.oaktwister.views.platforms.PlatformPane;
import com.oaktwister.views.platforms.PlatformPaneEvent;
import com.oaktwister.views.DialogResult;

import javafx.event.ActionEvent;

import java.util.HashMap;

public class PlatformsController {

    private final UIContext ui;

    private final PlatformsViewModel viewModel;
    private final FlowPage<PlatformPane> view;

    private final HashMap<PlatformViewModel, PlatformPane> platformsMap;
    private final ListItemAddedListener<PlatformViewModel> platformViewModelAddedListener;
    private final ListItemRemovedListener<PlatformViewModel> platformViewModelRemovedListener;

    public PlatformsController(UIContext ui) {
        this.ui = ui;
        viewModel = ui.viewModels().platforms();
        view = new FlowPage<>();
        platformsMap = new HashMap<>();
        platformViewModelAddedListener = new ListItemAddedListener<>(this::onPlatformViewModelAdded);
        platformViewModelRemovedListener = new ListItemRemovedListener<>(this::onPlatformViewModelRemoved);
    }

    public void initialize() {
        view.page().onAddActionProperty().set(this::onAddPlatformPane);
        viewModel.platformsProperty().addListener(platformViewModelAddedListener);
        viewModel.platformsProperty().addListener(platformViewModelRemovedListener);
    }

    public FlowPage<PlatformPane> getView() {
        return view;
    }

    public void reloadPlatforms() {
        viewModel.clear();
        viewModel.load();
    }

    private void onAddPlatformPane(ActionEvent actionEvent) {
        PlatformViewModel platformViewModel = ui.viewModels().platform();
        EditPlatformController dialogController = ui.controllers().editPlatform(platformViewModel);
        dialogController.showAndWait();
        if(dialogController.getResult() == DialogResult.SAVED) {
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
        view.panesProperty().add(platformPane);
        platformsMap.put(platformViewModel, platformPane);
    }

    private void onPlatformViewModelRemoved(PlatformViewModel platformViewModel) {
        PlatformPane platformPane = platformsMap.remove(platformViewModel);
        view.panesProperty().remove(platformPane);
    }

    private void onPlatformPaneClick(PlatformPaneEvent actionEvent) {
        PlatformPane platformPane = actionEvent.getPlatformPane();
        PlatformViewModel platformViewModel = MapUtil.getKeyByValue(platformsMap, platformPane);
        if(platformViewModel == null) {
            // TODO: Throw exception? This should never happen
            return;
        }
        EditPlatformController dialogController = ui.controllers().editPlatform(platformViewModel);
        if(dialogController.getResult() == DialogResult.SAVED) {
            // TODO: Save to database
            System.out.println("Updating platform to database");
        }
    }

    private void onPlatformPaneDeleteClick(PlatformPaneEvent actionEvent) {
        // TODO: Show DeletePlatformAlert
    }

}
