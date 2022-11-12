package com.oaktwister.app.views.accounts;

import com.oaktwister.app.core.UIContext;
import com.oaktwister.app.utils.Lazy;
import com.oaktwister.app.utils.listeners.ListItemAddedListener;
import com.oaktwister.app.utils.listeners.ListItemRemovedListener;
import com.oaktwister.app.viewmodels.models.AccountViewModel;
import com.oaktwister.app.viewmodels.views.AccountsViewModel;
import com.oaktwister.app.views.Controller;
import com.oaktwister.app.views.widgets.crud.CrudFrame;
import com.oaktwister.app.views.widgets.crud.CrudPage;
import javafx.event.ActionEvent;
import java.util.HashMap;

public final class AccountsController extends Controller<CrudFrame> {

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
        crudFrame.onAddActionProperty().set(this::addAccount);
        crudFrame.onEditActionProperty().set(this::editAccount);
        crudFrame.onRemoveActionProperty().set(this::removeAccount);
        viewModel.accountsProperty().addListener(accountAddedListener);
        viewModel.accountsProperty().addListener(accountRemovedListener);
        return crudFrame;
    }

    private void addAccount(ActionEvent actionEvent) {
        // TODO
    }

    private void editAccount(ActionEvent actionEvent) {
        // TODO
    }

    private void removeAccount(ActionEvent actionEvent) {
        // TODO
    }

    private void onAccountAdded(AccountViewModel accountViewModel) {
        AccountPane accountPane = new AccountPane();
        accountPane.identifierProperty().bind(accountViewModel.idProperty());
        accountPane.createdAtProperty().bind(accountViewModel.createdAtProperty());
        accountPane.imageProperty().bind(accountViewModel.platform().imageProperty());
        accountPane.platformNameProperty().bind(accountViewModel.platform().nameProperty());
        accountPane.grantsCountProperty().bind(accountViewModel.grantMap().grantCountProperty());
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
