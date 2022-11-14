package com.oaktwister.app.views.accounts;

import com.oaktwister.app.core.UIContext;
import com.oaktwister.app.utils.listeners.ListItemAddedListener;
import com.oaktwister.app.utils.listeners.ListItemRemovedListener;
import com.oaktwister.app.viewmodels.models.AccountViewModel;
import com.oaktwister.app.viewmodels.views.AccountsViewModel;
import com.oaktwister.app.views.Controller;
import com.oaktwister.app.views.widgets.crud.CrudFrame;
import com.oaktwister.app.views.widgets.crud.CrudPage;
import com.oaktwister.app.views.widgets.dialogs.Alert;
import com.oaktwister.app.views.widgets.dialogs.AlertType;
import com.oaktwister.app.views.widgets.dialogs.DialogResult;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.util.HashMap;

public final class AccountsController extends Controller<CrudFrame> {

    private final static String DELETE_CONFIRMATION_MESSAGE =
            "Are you sure you want to delete %s account? \nThis action cannot be undone.";

    private final UIContext ui;
    private final AccountsViewModel viewModel;

    private final CrudFrame crudFrame;
    private final CrudPage<AccountPane> crudPage;

    private final HashMap<AccountViewModel, AccountPane> accountMapping;
    private final ListItemAddedListener<AccountViewModel> accountAddedListener;
    private final ListItemRemovedListener<AccountViewModel> accountRemovedListener;

    public AccountsController(UIContext ui) {
        this.ui = ui;
        viewModel = ui.viewModels().accounts();
        crudFrame = new CrudFrame();
        crudPage = new CrudPage<>();
        accountMapping = new HashMap<>();
        accountAddedListener = new ListItemAddedListener<>(this::onAccountAdded);
        accountRemovedListener = new ListItemRemovedListener<>(this::onAccountRemoved);
    }

    @Override
    protected CrudFrame initialize() {
        crudFrame.setContent(crudPage);
        crudPage.selectedPaneProperty().addListener((observable, oldValue, newValue) -> {
            AccountViewModel account = newValue.getGraphic().getAccount();
            viewModel.setSelectedAccount(account);
        });
        crudFrame.onAddActionProperty().set(this::addAccount);
        crudFrame.onRemoveActionProperty().set(this::removeAccount);
        crudFrame.onEditActionProperty().set(this::editAccount);
        viewModel.accountsProperty().addListener(accountAddedListener);
        viewModel.accountsProperty().addListener(accountRemovedListener);
        return crudFrame;
    }

    private void addAccount(ActionEvent actionEvent) {
        // TODO: Show EditAccountDialog with empty account
        // If dialog.result() == SAVE then
        // viewModel.add(dialog.account());
    }

    private void removeAccount(ActionEvent actionEvent) {
        AccountViewModel account = viewModel.getSelectedAccount();
        Alert alert = new Alert();
        alert.setAlertType(AlertType.CONFIRM);
        alert.setMessage(String.format(DELETE_CONFIRMATION_MESSAGE, account.idProperty().get().toString()));
        Stage stage = ui.navigation().getDialogStage(alert);
        alert.showAndWait(stage);
        DialogResult result = alert.resultProperty().get();
        if(result == DialogResult.YES) {
            viewModel.remove();
        }
    }

    private void editAccount(ActionEvent actionEvent) {
        // TODO: Show EditAccountDialog with selected account
        // If dialog.result() == SAVE then
        // viewModel.update();
    }

    private void onAccountAdded(AccountViewModel accountViewModel) {
        AccountPane accountPane = new AccountPane();
        accountPane.setAccount(accountViewModel);
        accountMapping.put(accountViewModel, accountPane);
        crudPage.add(accountPane);
    }

    private void onAccountRemoved(AccountViewModel accountViewModel) {
        AccountPane accountPane = accountMapping.remove(accountViewModel);
        crudPage.remove(accountPane);
    }

    public void reloadAccounts() {
        viewModel.clear();
        viewModel.load();
    }

}
