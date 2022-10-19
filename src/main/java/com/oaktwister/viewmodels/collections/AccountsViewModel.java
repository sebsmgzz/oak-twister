package com.oaktwister.viewmodels.collections;

import com.oaktwister.models.aggregators.Account;
import com.oaktwister.services.logging.Logger;
import com.oaktwister.services.repos.AccountsRepo;
import com.oaktwister.viewmodels.models.AccountViewModel;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

import java.util.List;

public class AccountsViewModel {

    private final AccountsRepo accountsRepo;
    private final Logger logger;

    private final SimpleListProperty<AccountViewModel> accountsProperty;

    public AccountsViewModel(AccountsRepo accountsRepo, Logger logger) {
        this.accountsRepo = accountsRepo;
        this.logger = logger;
        accountsProperty = new SimpleListProperty<>(FXCollections.observableArrayList());
    }

    public ReadOnlyListProperty<AccountViewModel> accountsProperty() {
        return accountsProperty;
    }

    public void loadAccounts() {
        logger.debug("Loading accounts");
        List<Account> accounts = accountsRepo.findAll();
        for(Account account : accounts) {
            AccountViewModel accountViewModel = new AccountViewModel();
            this.accountsProperty.add(accountViewModel);
            accountViewModel.bind(account);
        }
        logger.debug("Loaded %s accounts", accounts.size());
    }

}
