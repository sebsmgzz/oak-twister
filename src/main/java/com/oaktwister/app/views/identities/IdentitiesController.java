package com.oaktwister.app.views.identities;

import com.oaktwister.app.core.UIContext;
import com.oaktwister.app.utils.Lazy;
import com.oaktwister.app.utils.listeners.ListItemAddedListener;
import com.oaktwister.app.utils.listeners.ListItemRemovedListener;
import com.oaktwister.app.viewmodels.models.IdentityViewModel;
import com.oaktwister.app.viewmodels.views.IdentitiesViewModel;
import com.oaktwister.app.views.Controller;
import com.oaktwister.app.views.widgets.crud.CrudFrame;
import javafx.event.ActionEvent;

import java.util.HashMap;

public final class IdentitiesController extends Controller<CrudFrame> {

    private final UIContext ui;
    private final IdentitiesViewModel viewModel;

    private final CrudFrame crudFrame;
    private final IdentitiesTable identitiesTable;

    private final HashMap<IdentityViewModel, IdentityPane> identityMapping;
    private final ListItemAddedListener<IdentityViewModel> identityAddedListener;
    private final ListItemRemovedListener<IdentityViewModel> identityRemovedListener;

    public IdentitiesController(UIContext ui) {
        this.ui = ui;
        viewModel = ui.viewModels().identities();
        crudFrame = new CrudFrame();
        identitiesTable = new IdentitiesTable();
        identityMapping = new HashMap<>();
        identityAddedListener = new ListItemAddedListener<>(this::onIdentityAdded);
        identityRemovedListener = new ListItemRemovedListener<>(this::onIdentityRemoved);
    }

    @Override
    protected CrudFrame initialize() {
        crudFrame.setContent(identitiesTable);
        crudFrame.onAddActionProperty().set(this::addIdentity);
        crudFrame.onEditActionProperty().set(this::editIdentity);
        crudFrame.onRemoveActionProperty().set(this::removeIdentity);
        viewModel.identitiesProperty().addListener(identityAddedListener);
        viewModel.identitiesProperty().addListener(identityRemovedListener);
        return crudFrame;
    }


    private void addIdentity(ActionEvent actionEvent) {
        // TODO
    }

    private void editIdentity(ActionEvent actionEvent) {
        // TODO
    }

    private void removeIdentity(ActionEvent actionEvent) {
        // TODO
    }

    private void onIdentityAdded(IdentityViewModel identityViewModel) {
        IdentityPane identityPane = new IdentityPane();
        identityPane.identifierProperty().bind(identityViewModel.idProperty());
        identityPane.nameProperty().bind(identityViewModel.nameProperty());
        identityPane.grantsCountProperty().bind(identityViewModel.grantMap().grantCountProperty());
        identityPane.createdAtProperty().bind(identityViewModel.createdAtProperty());
        identityMapping.put(identityViewModel, identityPane);
        identitiesTable.itemsProperty().get().add(identityViewModel);
    }

    private void onIdentityRemoved(IdentityViewModel identityViewModel) {
        identityMapping.remove(identityViewModel);
        identitiesTable.itemsProperty().get().remove(identityViewModel);
    }

    public void reloadIdentities() {
        viewModel.clear();
        viewModel.load();
    }

}
