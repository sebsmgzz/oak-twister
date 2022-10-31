package com.oaktwister.controllers.controls;

import com.oaktwister.core.Navigation;
import com.oaktwister.services.resources.StringResources;
import com.oaktwister.utils.extensions.MapUtil;
import com.oaktwister.utils.listeners.ListItemAddedListener;
import com.oaktwister.utils.listeners.ListItemRemovedListener;
import com.oaktwister.viewmodels.collections.PlatformsViewModel;
import com.oaktwister.viewmodels.models.PlatformViewModel;
import com.oaktwister.views.controls.PagePane;
import com.oaktwister.views.controls.PlatformPane;
import com.oaktwister.views.controls.PlatformPaneEvent;
import com.oaktwister.views.dialogs.EditPlatformDialogResult;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.*;

public class PlatformsController implements Initializable {

    private final Navigation navigation;
    private final PlatformsViewModel viewModel;
    private final HashMap<PlatformViewModel, PlatformPane> platformsMap;

    private final PagePane<PlatformPane> platformsPane;
    private final ListItemAddedListener<PlatformViewModel> platformViewModelAddedListener;
    private final ListItemRemovedListener<PlatformViewModel> platformViewModelRemovedListener;

    public PlatformsController(Navigation navigation, PlatformsViewModel viewModel) {
        this.navigation = navigation;
        this.viewModel = viewModel;
        platformsMap = new HashMap<>();
        platformsPane = new PagePane<>();
        platformViewModelAddedListener = new ListItemAddedListener<>(this::onPlatformViewModelAdded);
        platformViewModelRemovedListener = new ListItemRemovedListener<>(this::onPlatformViewModelRemoved);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        platformsPane.titleProperty().set(StringResources.PLATFORMS);
        platformsPane.onAddActionProperty().set(this::onAddPlatformPane);
        viewModel.platformsProperty().addListener(platformViewModelAddedListener);
        viewModel.platformsProperty().addListener(platformViewModelRemovedListener);
    }

    public PagePane<PlatformPane> getView() {
        return platformsPane;
    }

    private void onAddPlatformPane(ActionEvent actionEvent) {
        PlatformViewModel platformViewModel = navigation.viewModelFactory.getPlatformViewModel();
        EditPlatformDialogResult result = navigation.showEditPlatformDialog(platformViewModel);
        if(result == EditPlatformDialogResult.SAVED) {
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
        platformsPane.panesProperty().add(platformPane);
        platformsMap.put(platformViewModel, platformPane);
    }

    private void onPlatformViewModelRemoved(PlatformViewModel platformViewModel) {
        PlatformPane platformPane = platformsMap.remove(platformViewModel);
        platformsPane.panesProperty().remove(platformPane);
    }

    private void onPlatformPaneClick(PlatformPaneEvent actionEvent) {
        PlatformPane platformPane = actionEvent.getPlatformPane();
        PlatformViewModel platformViewModel = MapUtil.getKeyByValue(platformsMap, platformPane);
        if(platformViewModel == null) {
            // TODO: Throw exception? This should never happen
            return;
        }
        EditPlatformDialogResult result = navigation.showEditPlatformDialog(platformViewModel);
        if(result == EditPlatformDialogResult.SAVED) {
            // TODO: Save to database
            System.out.println("Updating platform to database");
        }
    }

    private void onPlatformPaneDeleteClick(PlatformPaneEvent actionEvent) {
        // TODO: Show DeletePlatformAlert
    }

}
