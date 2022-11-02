package com.oaktwister.controllers.controls;

import com.oaktwister.core.UIContext;
import com.oaktwister.events.IdentityPaneActionEvent;
import com.oaktwister.services.resources.StringResources;
import com.oaktwister.utils.listeners.ListItemAddedListener;
import com.oaktwister.utils.listeners.ListItemRemovedListener;
import com.oaktwister.viewmodels.collections.IdentitiesViewModel;
import com.oaktwister.viewmodels.models.IdentityViewModel;
import com.oaktwister.views.identities.IdentitiesPage;
import com.oaktwister.views.identities.IdentityPane;

import javafx.event.ActionEvent;

import java.util.HashMap;

public class IdentitiesController {

    private final UIContext ui;

    private final IdentitiesViewModel viewModel;
    private final IdentitiesPage view;

    private final HashMap<IdentityViewModel, IdentityPane> identitiesMap;
    private final ListItemAddedListener<IdentityViewModel> identityViewModelAddedListener;
    private final ListItemRemovedListener<IdentityViewModel> identityViewModelRemovedListener;

    public IdentitiesController(UIContext ui) {
        this.ui = ui;
        viewModel = ui.viewModels().identities();
        view = new IdentitiesPage();
        identitiesMap = new HashMap<>();
        identityViewModelAddedListener = new ListItemAddedListener<>(this::onIdentityViewModelAdded);
        identityViewModelRemovedListener = new ListItemRemovedListener<>(this::onIdentityViewModelRemoved);
    }

    public void initialize() {
        view.titleProperty().set(StringResources.IDENTITIES);
        view.onAddActionProperty().set(this::onAddIdentityPane);
        viewModel.identitiesProperty().addListener(identityViewModelAddedListener);
        viewModel.identitiesProperty().addListener(identityViewModelRemovedListener);
    }

    public IdentitiesPage getView() {
        return view;
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
        view.identitiesProperty().get().add(identityViewModel);
        identitiesMap.put(identityViewModel, identityPane);
    }

    private void onIdentityViewModelRemoved(IdentityViewModel identityViewModel) {
        IdentityPane identityPane = identitiesMap.remove(identityViewModel);
        view.identitiesProperty().get().remove(identityViewModel);
    }

    private void onIdentityPaneMainAction(IdentityPaneActionEvent event) {
        // TODO: Show EditIdentityDialog
    }

    private void onIdentityPaneDeleteAction(IdentityPaneActionEvent event) {
        // TODO: Show DeleteIdentityAlert
    }

}
