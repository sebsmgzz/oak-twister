package com.oaktwister.views.main;

import com.oaktwister.core.ViewFactory;
import com.oaktwister.viewmodels.landing.LandingViewModel;
import com.oaktwister.viewmodels.main.AccountsViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AccountsViewController implements Initializable {

    private final ViewFactory viewFactory;
    private final AccountsViewModel viewModel;

    @FXML
    public FlowPane flowPane;

    public AccountsViewController(ViewFactory viewFactory, AccountsViewModel viewModel) {
        this.viewFactory = viewFactory;
        this.viewModel = viewModel;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

}
