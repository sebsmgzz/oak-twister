package com.oaktwister.controllers.controls;

import com.oaktwister.core.UIContext;
import com.oaktwister.events.AccountPaneActionEvent;
import com.oaktwister.services.resources.StringResources;
import com.oaktwister.utils.listeners.ListItemAddedListener;
import com.oaktwister.utils.listeners.ListItemRemovedListener;
import com.oaktwister.viewmodels.collections.AccountsViewModel;
import com.oaktwister.viewmodels.models.AccountViewModel;
import com.oaktwister.views.controls.AccountPane;
import com.oaktwister.views.controls.PagePane;
import javafx.event.ActionEvent;

import java.util.HashMap;

public class AccountsController {

    private final UIContext ui;

    private final AccountsViewModel viewModel;
    private final PagePane<AccountPane> view;

    private final HashMap<AccountViewModel, AccountPane> accountsMap;
    private final ListItemAddedListener<AccountViewModel> accountViewModelAddedListener;
    private final ListItemRemovedListener<AccountViewModel> accountViewModelRemovedListener;

    public AccountsController(UIContext ui) {
        this.ui = ui;
        viewModel = ui.viewModels().accounts();
        view = new PagePane<>();
        accountsMap = new HashMap<>();
        accountViewModelAddedListener = new ListItemAddedListener<>(this::onAccountViewModelAdded);
        accountViewModelRemovedListener = new ListItemRemovedListener<>(this::onAccountViewModelRemoved);
    }

    public void initialize() {
        view.titleProperty().set(StringResources.ACCOUNTS);
        view.onAddActionProperty().set(this::onAddAccountPane);
        viewModel.accountsProperty().addListener(accountViewModelAddedListener);
        viewModel.accountsProperty().addListener(accountViewModelRemovedListener);
    }

    public PagePane<AccountPane> getView() {
        return view;
    }

    public void reloadAccounts() {
        viewModel.clear();
        viewModel.load();
    }

    private void onAddAccountPane(ActionEvent actionEvent) {
        // TODO: Show EditAccountDialog
    }

    private void onAccountViewModelAdded(AccountViewModel accountViewModel) {
        AccountPane accountPane = new AccountPane();
        accountPane.onMainActionProperty().set(this::onAccountPaneMainAction);
        accountPane.onDeleteActionProperty().set(this::onAccountPaneDeleteAction);
        accountPane.identifierProperty().bind(accountViewModel.idProperty());
        accountPane.createdAtProperty().bind(accountViewModel.createdAtProperty());
        accountPane.imageProperty().bind(accountViewModel.platform().imageProperty());
        accountPane.platformNameProperty().bind(accountViewModel.platform().nameProperty());
        accountPane.grantsCountProperty().bind(accountViewModel.grantMap().grantCountProperty());
        view.panesProperty().add(accountPane);
        accountsMap.put(accountViewModel, accountPane);
    }

    private void onAccountViewModelRemoved(AccountViewModel accountViewModel) {
        AccountPane accountPane = accountsMap.remove(accountViewModel);
        view.panesProperty().remove(accountPane);
    }

    private void onAccountPaneMainAction(AccountPaneActionEvent event) {
        // TODO: Show EditAccountDialog
    }

    private void onAccountPaneDeleteAction(AccountPaneActionEvent event) {
        // TODO: Show DeleteAccountAlert
    }

}
