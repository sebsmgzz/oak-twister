package com.oaktwister.views.windows.main;

import com.oaktwister.core.Navigation;
import com.oaktwister.services.resources.StringResources;
import com.oaktwister.utils.listeners.ListItemAddedListener;
import com.oaktwister.utils.listeners.ListItemRemovedListener;
import com.oaktwister.viewmodels.collections.PlatformsViewModel;
import com.oaktwister.viewmodels.models.PlatformViewModel;
import com.oaktwister.views.controls.pages.PagePane;
import com.oaktwister.views.controls.platforms.PlatformPane;
import com.oaktwister.views.dialogs.platforms.EditPlatformDialogResult;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

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

    private void onPlatformPaneClick(ActionEvent actionEvent) {
        // TODO: Show EditPlatformDialog
    }

    private void onPlatformPaneDeleteClick(ActionEvent actionEvent) {
        // TODO: Show DeletePlatformAlert
    }

}
