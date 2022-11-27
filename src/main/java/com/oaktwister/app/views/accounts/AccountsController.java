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

    // Context
    private final UIContext ui;
    private final AccountsViewModel viewModel;

    // UI
    private final CrudFrame crudFrame;
    private final CrudPage<AccountPane> crudPage;

    // Listeners and mappings
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
        crudFrame.onAddActionProperty().set(this::onAddAccount);
        crudFrame.onRemoveActionProperty().set(this::onRemoveAccount);
        crudFrame.onEditActionProperty().set(this::onEditAccount);
        viewModel.accountsProperty().addListener(accountAddedListener);
        viewModel.accountsProperty().addListener(accountRemovedListener);
        return crudFrame;
    }

    private void onAddAccount(ActionEvent actionEvent) {
        // TODO: Show EditAccountDialog with empty account
        // If dialog.result() == SAVE then
        // viewModel.add(dialog.account());
    }

    private void onEditAccount(ActionEvent actionEvent) {
        // TODO: Show EditAccountDialog with selected account
        // If dialog.result() == SAVE then
        // viewModel.update();
    }

    private void onRemoveAccount(ActionEvent actionEvent) {
        AccountViewModel account = viewModel.getSelectedAccount();
        Alert alert = new Alert();
        alert.setAlertType(AlertType.CONFIRM);
        alert.setMessage(String.format(DELETE_CONFIRMATION_MESSAGE, account.getId()));
        Stage stage = ui.stages().getDialogStage(alert);
        alert.showAndWait(stage);
        DialogResult result = alert.resultProperty().get();
        if(result == DialogResult.YES) {
            boolean deleted = account.delete();
            if(deleted) {
                viewModel.removeAccount(account);
            }
        }
    }

    private void onAccountAdded(AccountViewModel account) {
        AccountPane accountPane = new AccountPane();
        accountPane.setAccount(account);
        accountMapping.put(account, accountPane);
        crudPage.add(accountPane);
    }

    private void onAccountRemoved(AccountViewModel account) {
        AccountPane accountPane = accountMapping.remove(account);
        crudPage.remove(accountPane);
    }

    public void reloadAccounts() {
        viewModel.clearAccounts();
        viewModel.load();
    }

}
