package com.oaktwister.viewmodels.collections;

import com.oaktwister.models.aggregators.Account;
import com.oaktwister.services.logging.Logger;
import com.oaktwister.services.repos.AccountsRepo;
import com.oaktwister.viewmodels.models.AccountViewModel;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class AccountsViewModel {

    private final AccountsRepo accountsRepo;
    private final Logger logger;

    private final ObservableList<AccountViewModel> accounts;

    public AccountsViewModel(AccountsRepo accountsRepo, Logger logger) {
        this.accountsRepo = accountsRepo;
        this.logger = logger;
        accounts = new SimpleListProperty<>(FXCollections.observableArrayList());
    }

    public ObservableList<AccountViewModel> accountsProperty() {
        return accounts;
    }

    public AccountViewModel getAccount(int index) {
        return accounts.get(index);
    }

    public void loadAccounts() {
        logger.debug("Loading accounts");
        List<Account> accounts = accountsRepo.findAll();
        for(Account account : accounts) {
            this.accounts.add(new AccountViewModel(account));
        }
        logger.debug("Loaded %s accounts", accounts.size());
    }

}
