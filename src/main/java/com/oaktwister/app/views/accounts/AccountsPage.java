package com.oaktwister.app.views.accounts;

import com.oaktwister.app.annotations.ViewDescriptor;
import com.oaktwister.app.core.UIContext;
import com.oaktwister.app.events.AccountPaneActionEvent;
import com.oaktwister.app.services.resources.ViewResources;
import com.oaktwister.app.utils.extensions.FXMLUtil;
import com.oaktwister.app.utils.listeners.ListItemAddedListener;
import com.oaktwister.app.utils.listeners.ListItemRemovedListener;
import com.oaktwister.app.viewmodels.collections.AccountsViewModel;
import com.oaktwister.app.viewmodels.models.AccountViewModel;
import com.oaktwister.app.views.widgets.CrudPage;
import com.oaktwister.app.views.widgets.FlowPage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

@ViewDescriptor(location = ViewResources.Accounts.PAGE)
public class AccountsPage extends AnchorPane implements Initializable {

    private final UIContext ui;
    private final AccountsViewModel viewModel;

    @FXML private FlowPage<AccountPane> flowPage;
    @FXML private CrudPage crudPage;

    private final HashMap<AccountViewModel, AccountPane> accountsMap;
    private final ListItemAddedListener<AccountViewModel> accountViewModelAddedListener;
    private final ListItemRemovedListener<AccountViewModel> accountViewModelRemovedListener;

    public AccountsPage(UIContext ui) {
        this.ui = ui;
        viewModel = ui.viewModels().accounts();
        accountsMap = new HashMap<>();
        accountViewModelAddedListener = new ListItemAddedListener<>(this::onAccountViewModelAdded);
        accountViewModelRemovedListener = new ListItemRemovedListener<>(this::onAccountViewModelRemoved);
        FXMLUtil.loadControl(AccountsPage.class, this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        crudPage.onAddActionProperty().set(this::onAddAccountPane);
        viewModel.accountsProperty().addListener(accountViewModelAddedListener);
        viewModel.accountsProperty().addListener(accountViewModelRemovedListener);
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
        flowPage.panesProperty().add(accountPane);
        accountsMap.put(accountViewModel, accountPane);
    }

    private void onAccountViewModelRemoved(AccountViewModel accountViewModel) {
        AccountPane accountPane = accountsMap.remove(accountViewModel);
        flowPage.panesProperty().remove(accountPane);
    }

    private void onAccountPaneMainAction(AccountPaneActionEvent event) {
        // TODO: Show EditAccountDialog
    }

    private void onAccountPaneDeleteAction(AccountPaneActionEvent event) {
        // TODO: Show DeleteAccountAlert
    }

    public void reloadAccounts() {
        viewModel.clear();
        viewModel.load();
    }

}
