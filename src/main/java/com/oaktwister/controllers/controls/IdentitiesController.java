package com.oaktwister.controllers.controls;

import com.oaktwister.core.Navigation;
import com.oaktwister.services.resources.StringResources;
import com.oaktwister.utils.listeners.ListItemAddedListener;
import com.oaktwister.utils.listeners.ListItemRemovedListener;
import com.oaktwister.viewmodels.collections.IdentitiesViewModel;
import com.oaktwister.viewmodels.models.IdentityViewModel;
import com.oaktwister.views.controls.IdentityPane;
import com.oaktwister.views.controls.PagePane;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class IdentitiesController implements Initializable {

    private final Navigation navigation;
    private final IdentitiesViewModel viewModel;
    private final HashMap<IdentityViewModel, IdentityPane> identitiesMap;

    private final PagePane<IdentityPane> identitiesPane;
    private final ListItemAddedListener<IdentityViewModel> identityViewModelAddedListener;
    private final ListItemRemovedListener<IdentityViewModel> identityViewModelRemovedListener;

    public IdentitiesController(Navigation navigation, IdentitiesViewModel viewModel) {
        this.navigation = navigation;
        this.viewModel = viewModel;
        identitiesMap = new HashMap<>();
        identitiesPane = new PagePane<>();
        identityViewModelAddedListener = new ListItemAddedListener<>(this::onIdentityViewModelAdded);
        identityViewModelRemovedListener = new ListItemRemovedListener<>(this::onIdentityViewModelRemoved);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        identitiesPane.titleProperty().set(StringResources.IDENTITIES);
        identitiesPane.onAddActionProperty().set(this::onAddIdentityPane);
        viewModel.identitiesProperty().addListener(identityViewModelAddedListener);
        viewModel.identitiesProperty().addListener(identityViewModelRemovedListener);
    }

    public PagePane<IdentityPane> getView() {
        return identitiesPane;
    }

    private void onAddIdentityPane(ActionEvent actionEvent) {
        // TODO: Show EditIdentityDialog
    }

    private void onIdentityViewModelAdded(IdentityViewModel identityViewModel) {
        IdentityPane identityPane = new IdentityPane();
        identityPane.onMainActionProperty().set(this::onIdentityPaneClick);
        identityPane.onDeleteActionProperty().set(this::onIdentityPaneDeleteClick);
        identityPane.identifierProperty().bind(identityViewModel.idProperty());
        identityPane.grantsCountProperty().bind(identityViewModel.grantMap().grantCountProperty());
        identityPane.createdAtProperty().bind(identityViewModel.createdAtProperty());
        identitiesPane.panesProperty().add(identityPane);
        identitiesMap.put(identityViewModel, identityPane);
    }

    private void onIdentityViewModelRemoved(IdentityViewModel identityViewModel) {
        IdentityPane identityPane = identitiesMap.remove(identityViewModel);
        identitiesPane.panesProperty().remove(identityPane);
    }

    private void onIdentityPaneClick(ActionEvent actionEvent) {
        // TODO: Show EditIdentityDialog
    }

    private void onIdentityPaneDeleteClick(ActionEvent actionEvent) {
        // TODO: Show DeleteIdentityAlert
    }

}
