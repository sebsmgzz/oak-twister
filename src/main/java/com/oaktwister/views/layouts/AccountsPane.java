package com.oaktwister.views.layouts;

import com.oaktwister.core.ViewHandler;
import com.oaktwister.services.Resources;
import com.oaktwister.viewmodels.collections.AccountsViewModel;
import com.oaktwister.viewmodels.models.AccountViewModel;
import com.oaktwister.views.View;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AccountsPane extends VBox implements View {

    private final ViewHandler viewHandler;
    private final AccountsViewModel viewModel;

    @FXML private Label titleLabel;
    @FXML private ListView<AccountViewModel> listView;
    @FXML private Button addButton;

    public AccountsPane(ViewHandler viewHandler, AccountsViewModel viewModel) {
        super();
        this.viewHandler = viewHandler;
        this.viewModel = viewModel;
        viewHandler.loadCustomView(this);
    }

    @Override
    public String getViewLocation() {
        return Resources.Views.Layouts.ACCOUNTS_PANE;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Bindings
        this.widthProperty().addListener(((observable, oldValue, newValue) ->
                listView.setPrefWidth(newValue.doubleValue())));
        this.heightProperty().addListener(((observable, oldValue, newValue) ->
                listView.setPrefHeight(newValue.doubleValue())));

        // Data loaders
        listView.setCellFactory(listView -> {
            int accountIndex = listView.getItems().size() - 1;
            AccountViewModel account = viewModel.getAccount(accountIndex);
            return viewHandler.getAccountCell(account);
        });
        listView.setItems(viewModel.accountsProperty());

        // TODO: How is the AccountCell reading the AccountViewModel?

        // Load data
        viewModel.loadAccounts();

    }

    public StringProperty titleProperty() {
        return titleLabel.textProperty();
    }

    public String getTitle() {
        return titleProperty().get();
    }

    public void setTitle(String title) {
        titleProperty().set(title);
    }

    public ObjectProperty<ObservableList<AccountViewModel>> accountsProperty() {
        return listView.itemsProperty();
    }

    public ObservableList<AccountViewModel> getAccounts() {
        return accountsProperty().get();
    }

    public void setAccounts(ObservableList<AccountViewModel> accounts) {
        accountsProperty().set(accounts);
    }

    public ObjectProperty<EventHandler<ActionEvent>> onAddActionProperty() {
        return addButton.onActionProperty();
    }

    public EventHandler<ActionEvent> getOnAddAction() {
        return onAddActionProperty().get();
    }

    public void setOnAddAction(EventHandler<ActionEvent> onAddAction) {
        onAddActionProperty().set(onAddAction);
    }

}
