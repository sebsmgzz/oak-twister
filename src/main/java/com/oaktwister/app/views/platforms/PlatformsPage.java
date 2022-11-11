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
import com.oaktwister.app.views.widgets.crud.CrudFrame;
import com.oaktwister.app.views.widgets.crud.CrudPage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

@ViewDescriptor(location = ViewResources.Platforms.PAGE)
public class PlatformsPage extends AnchorPane implements Initializable {

    private final UIContext ui;
    private final PlatformsViewModel viewModel;

    @FXML private CrudPage<PlatformPane> crudPage;
    @FXML private CrudFrame crudFrame;

    private final HashMap<PlatformViewModel, PlatformPane> platformsMap;
    private final ListItemAddedListener<PlatformViewModel> platformAddedListener;
    private final ListItemRemovedListener<PlatformViewModel> platformRemovedListener;

    public PlatformsPage(UIContext ui) {
        this.ui = ui;
        viewModel = ui.viewModels().platforms();
        platformsMap = new HashMap<>();
        platformAddedListener = new ListItemAddedListener<>(this::onPlatformAdded);
        platformRemovedListener = new ListItemRemovedListener<>(this::onPlatformRemoved);
        FXMLUtil.loadControl(this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        crudFrame.onAddActionProperty().set(this::addPlatform);
        crudFrame.onEditActionProperty().set(this::editPlatform);
        crudFrame.onRemoveActionProperty().set(this::removePlatform);
        viewModel.platformsProperty().addListener(platformAddedListener);
        viewModel.platformsProperty().addListener(platformRemovedListener);
    }

    private void addPlatform(ActionEvent actionEvent) {
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

    private void editPlatform(ActionEvent actionEvent) {
        // TODO
    }

    private void removePlatform(ActionEvent actionEvent) {
        // TODO
    }

    private void onPlatformAdded(PlatformViewModel platformViewModel) {
        PlatformPane platformPane = new PlatformPane();
        platformPane.identifierProperty().bind(platformViewModel.idProperty());
        platformPane.imageProperty().bind(platformViewModel.imageProperty());
        platformPane.nameProperty().bind(platformViewModel.nameProperty());
        platformPane.createdAtProperty().bind(platformViewModel.createdAtProperty());
        crudPage.add(platformPane);
        platformsMap.put(platformViewModel, platformPane);
    }

    private void onPlatformRemoved(PlatformViewModel platformViewModel) {
        PlatformPane platformPane = platformsMap.remove(platformViewModel);
        crudPage.remove(platformPane);
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

    public void reloadPlatforms() {
        viewModel.clear();
        viewModel.load();
    }

}
