package com.oaktwister.controllers.controls;

import com.oaktwister.core.Navigation;
import com.oaktwister.services.resources.StringResources;
import com.oaktwister.utils.listeners.ListItemAddedListener;
import com.oaktwister.utils.listeners.ListItemRemovedListener;
import com.oaktwister.viewmodels.collections.AccountsViewModel;
import com.oaktwister.viewmodels.models.AccountViewModel;
import com.oaktwister.views.controls.AccountPane;
import com.oaktwister.views.controls.PagePane;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class AccountsController implements Initializable {

    private final Navigation navigation;
    private final AccountsViewModel viewModel;
    private final HashMap<AccountViewModel, AccountPane> accountsMap;

    private final PagePane<AccountPane> accountsPane;
    private final ListItemAddedListener<AccountViewModel> accountViewModelAddedListener;
    private final ListItemRemovedListener<AccountViewModel> accountViewModelRemovedListener;

    public AccountsController(Navigation navigation, AccountsViewModel viewModel) {
        this.navigation = navigation;
        this.viewModel = viewModel;
        accountsMap = new HashMap<>();
        accountsPane = new PagePane<>();
        accountViewModelAddedListener = new ListItemAddedListener<>(this::onAccountPaneAdded);
        accountViewModelRemovedListener = new ListItemRemovedListener<>(this::onAccountPaneRemoved);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        accountsPane.titleProperty().set(StringResources.ACCOUNTS);
        accountsPane.onAddActionProperty().set(this::onAddAccountPane);
        viewModel.accountsProperty().addListener(accountViewModelAddedListener);
        viewModel.accountsProperty().addListener(accountViewModelRemovedListener);
    }

    public PagePane<AccountPane> getView() {
        return accountsPane;
    }

    private void onAddAccountPane(ActionEvent actionEvent) {
        // TODO: Show EditAccountDialog
    }

    private void onAccountPaneAdded(AccountViewModel accountViewModel) {
        AccountPane accountPane = new AccountPane();
        accountPane.onMainActionProperty().set(this::onAccountPaneClick);
        accountPane.onDeleteActionProperty().set(this::onAccountPaneDeleteClick);
        accountPane.identifierProperty().bind(accountViewModel.idProperty());
        accountPane.createdAtProperty().bind(accountViewModel.createdAtProperty());
        accountPane.imageProperty().bind(accountViewModel.platform().imageProperty());
        accountPane.platformNameProperty().bind(accountViewModel.platform().nameProperty());
        accountPane.grantsCountProperty().bind(accountViewModel.grantMap().grantCountProperty());
        accountsPane.panesProperty().add(accountPane);
        accountsMap.put(accountViewModel, accountPane);
    }

    private void onAccountPaneRemoved(AccountViewModel accountViewModel) {
        AccountPane accountPane = accountsMap.remove(accountViewModel);
        accountsPane.panesProperty().remove(accountPane);
    }

    private void onAccountPaneClick(ActionEvent actionEvent) {
        // TODO: Show EditAccountDialog
    }

    private void onAccountPaneDeleteClick(ActionEvent actionEvent) {
        // TODO: Show DeleteAccountAlert
    }

}
