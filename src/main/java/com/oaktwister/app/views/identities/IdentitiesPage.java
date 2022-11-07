package com.oaktwister.app.views.identities;

import com.oaktwister.app.annotations.ViewDescriptor;
import com.oaktwister.app.core.UIContext;
import com.oaktwister.app.events.IdentityPaneActionEvent;
import com.oaktwister.app.services.resources.ViewResources;
import com.oaktwister.app.utils.extensions.FXMLUtil;

import com.oaktwister.app.utils.listeners.ListItemAddedListener;
import com.oaktwister.app.utils.listeners.ListItemRemovedListener;
import com.oaktwister.app.viewmodels.collections.IdentitiesViewModel;
import com.oaktwister.app.viewmodels.models.IdentityViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

@ViewDescriptor(location = ViewResources.Identities.PAGE)
public class IdentitiesPage extends AnchorPane implements Initializable {

    private final UIContext ui;
    private final IdentitiesViewModel viewModel;

    @FXML private IdentitiesTable identitiesTable;

    private final HashMap<IdentityViewModel, IdentityPane> identitiesMap;
    private final ListItemAddedListener<IdentityViewModel> identityViewModelAddedListener;
    private final ListItemRemovedListener<IdentityViewModel> identityViewModelRemovedListener;

    public IdentitiesPage(UIContext ui) {
        this.ui = ui;
        viewModel = ui.viewModels().identities();
        identitiesMap = new HashMap<>();
        identityViewModelAddedListener = new ListItemAddedListener<>(this::onIdentityViewModelAdded);
        identityViewModelRemovedListener = new ListItemRemovedListener<>(this::onIdentityViewModelRemoved);
        FXMLUtil.loadControl(IdentitiesPage.class, this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        viewModel.identitiesProperty().addListener(identityViewModelAddedListener);
        viewModel.identitiesProperty().addListener(identityViewModelRemovedListener);
    }
    public void reloadIdentities() {
        viewModel.clear();
        viewModel.load();
    }

    private void onAddIdentityPane(ActionEvent actionEvent) {
        // TODO: Show EditIdentityDialog
    }

    private void onIdentityViewModelAdded(IdentityViewModel identityViewModel) {
        IdentityPane identityPane = new IdentityPane();
        identityPane.onMainActionProperty().set(this::onIdentityPaneMainAction);
        identityPane.onDeleteActionProperty().set(this::onIdentityPaneDeleteAction);
        identityPane.identifierProperty().bind(identityViewModel.idProperty());
        identityPane.nameProperty().bind(identityViewModel.nameProperty());
        identityPane.grantsCountProperty().bind(identityViewModel.grantMap().grantCountProperty());
        identityPane.createdAtProperty().bind(identityViewModel.createdAtProperty());
        identitiesMap.put(identityViewModel, identityPane);
        identitiesTable.itemsProperty().get().add(identityViewModel);
    }

    private void onIdentityViewModelRemoved(IdentityViewModel identityViewModel) {
        identitiesMap.remove(identityViewModel);
        identitiesTable.itemsProperty().get().remove(identityViewModel);
    }

    private void onIdentityPaneMainAction(IdentityPaneActionEvent event) {
        // TODO: Show EditIdentityDialog
    }

    private void onIdentityPaneDeleteAction(IdentityPaneActionEvent event) {
        // TODO: Show DeleteIdentityAlert
    }

}
