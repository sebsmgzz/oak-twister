package com.oaktwister.app.viewmodels.views;

import com.oaktwister.app.core.ViewModelFactory;
import com.oaktwister.domain.models.Account;
import com.oaktwister.app.services.logging.Logger;
import com.oaktwister.infrastructure.repos.AccountsRepo;
import com.oaktwister.app.viewmodels.models.AccountViewModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AccountsViewModel {

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

    public ReadOnlyListProperty<AccountViewModel> accountsProperty() {
        return accountsProperty;
    }
    public ObservableList<AccountViewModel> getAccounts() {
        return accountsProperty().get();
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

    public void load() {
        logger.debug("Loading accounts");
        List<Account> accounts = accountsRepo.findAll();
        for(Account account : accounts) {
            AccountViewModel accountViewModel = viewModelFactory.account();
            accountViewModel.setAccount(account);
            accountsProperty.add(accountViewModel);
        }
        logger.debug("Loaded %s accounts", accounts.size());
    }

    public void clear() {
        accountsProperty.clear();
    }

    public boolean add(@NotNull AccountViewModel accountViewModel) {
        Account account = accountViewModel.getAccount();
        return accountsRepo.add(account);
    }

    public boolean remove() {
        AccountViewModel accountViewModel = getSelectedAccount();
        if(accountViewModel == null) {
            return false;
        }
        Account account = accountViewModel.getAccount();
        return accountsRepo.remove(account);
    }

    public boolean update() {
        AccountViewModel accountViewModel = getSelectedAccount();
        if(accountViewModel == null) {
            return false;
        }
        Account account = accountViewModel.getAccount();
        return accountsRepo.update(account);
    }

}
