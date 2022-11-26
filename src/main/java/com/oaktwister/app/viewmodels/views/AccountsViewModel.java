package com.oaktwister.app.viewmodels.views;

import com.oaktwister.app.core.ViewModelFactory;
import com.oaktwister.app.viewmodels.ErrorViewModel;
import com.oaktwister.domain.exceptions.InvalidSessionPropertyException;
import com.oaktwister.domain.models.accounts.Account;
import com.oaktwister.app.services.logging.Logger;
import com.oaktwister.infrastructure.repos.AccountsRepo;
import com.oaktwister.app.viewmodels.models.AccountViewModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.IOException;
import java.util.List;

public class AccountsViewModel extends ErrorViewModel {

    private final ViewModelFactory viewModelFactory;
    private final AccountsRepo accountsRepo;
    private final Logger logger;

    private final SimpleObjectProperty<AccountViewModel> selectedAccountProperty;
    private final SimpleListProperty<AccountViewModel> accountsProperty;

    public AccountsViewModel(ViewModelFactory viewModelFactory, AccountsRepo accountsRepo, Logger logger) {
        this.viewModelFactory = viewModelFactory;
        this.accountsRepo = accountsRepo;
        this.logger = logger;
        selectedAccountProperty = new SimpleObjectProperty<>();
        accountsProperty = new SimpleListProperty<>(FXCollections.observableArrayList());
    }

    public void load() {
        try {
            logger.debug("Loading accounts");
            List<Account> accounts = accountsRepo.findAll();
            for(Account account : accounts) {
                AccountViewModel accountViewModel = viewModelFactory.account();
                accountViewModel.setAccount(account);
                accountsProperty.add(accountViewModel);
            }
            logger.debug("Loaded %s accounts", accounts.size());
        } catch (IOException | InvalidSessionPropertyException ex) {
            logger.error(ex);
            setError(ex);
        }
    }

    public ReadOnlyListProperty<AccountViewModel> accountsProperty() {
        return accountsProperty;
    }
    public ObservableList<AccountViewModel> getAccounts() {
        return accountsProperty().get();
    }
    public boolean addAccount(AccountViewModel account) {
        return accountsProperty().add(account);
    }
    public boolean addAccounts(AccountViewModel... accounts) {
        return accountsProperty().addAll(accounts);
    }
    public boolean removeAccount(AccountViewModel account) {
        return accountsProperty().remove(account);
    }
    public boolean removeAccounts(AccountViewModel... accounts) {
        return accountsProperty().removeAll(accounts);
    }
    public void clearAccounts() {
        accountsProperty().clear();
    }

    public ObjectProperty<AccountViewModel> selectedAccountProperty() {
        return selectedAccountProperty;
    }
    public AccountViewModel getSelectedAccount() {
        return selectedAccountProperty().get();
    }
    public void setSelectedAccount(AccountViewModel value) {
        selectedAccountProperty().set(value);
    }

}
