package com.oaktwister.controllers.controls;

import com.oaktwister.core.Navigation;
import com.oaktwister.core.ViewModelFactory;
import com.oaktwister.events.IdentityPaneActionEvent;
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

public class IdentitiesController {

    private final Navigation navigation;

    private final IdentitiesViewModel viewModel;
    private final PagePane<IdentityPane> view;

    private final HashMap<IdentityViewModel, IdentityPane> identitiesMap;
    private final ListItemAddedListener<IdentityViewModel> identityViewModelAddedListener;
    private final ListItemRemovedListener<IdentityViewModel> identityViewModelRemovedListener;

    public IdentitiesController(Navigation navigation, ViewModelFactory viewModelFactory) {
        this.navigation = navigation;
        viewModel = viewModelFactory.getIdentitiesViewModel();
        view = new PagePane<>();
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

    public PagePane<IdentityPane> getView() {
        return view;
    }

    public void onShowing() {
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
        identityPane.grantsCountProperty().bind(identityViewModel.grantMap().grantCountProperty());
        identityPane.createdAtProperty().bind(identityViewModel.createdAtProperty());
        view.panesProperty().add(identityPane);
        identitiesMap.put(identityViewModel, identityPane);
    }

    private void onIdentityViewModelRemoved(IdentityViewModel identityViewModel) {
        IdentityPane identityPane = identitiesMap.remove(identityViewModel);
        view.panesProperty().remove(identityPane);
    }

    private void onIdentityPaneMainAction(IdentityPaneActionEvent event) {
        // TODO: Show EditIdentityDialog
    }

    private void onIdentityPaneDeleteAction(IdentityPaneActionEvent event) {
        // TODO: Show DeleteIdentityAlert
    }

}
