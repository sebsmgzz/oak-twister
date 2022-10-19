package com.oaktwister.views.layouts;

import com.oaktwister.core.ViewHandler;
import com.oaktwister.services.Resources;
import com.oaktwister.viewmodels.collections.AccountsViewModel;
import com.oaktwister.viewmodels.models.AccountViewModel;
import com.oaktwister.viewmodels.util.DualChangeListener;
import com.oaktwister.views.View;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class AccountsPane extends VBox implements View {

    private final ViewHandler viewHandler;
    private final AccountsViewModel viewModel;

    @FXML private Label titleLabel;
    @FXML private ScrollPane scrollPane;
    @FXML private VBox vbox;
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

        // View bindings
        this.widthProperty().addListener(((observable, oldValue, newValue) ->
                scrollPane.setPrefWidth(newValue.doubleValue())));
        this.heightProperty().addListener(((observable, oldValue, newValue) ->
                scrollPane.setPrefHeight(newValue.doubleValue())));

        // Data bindings
        viewModel.accountsProperty().addListener(new DualChangeListener<>(
            accountViewModel -> vbox.getChildren().add(viewHandler.getAccountBox(accountViewModel)),
            accountViewModel -> {
                /* TODO: Look up the AccountBox that references this accountViewModel and remove it */
            }
        ));

        // Load data
        viewModel.loadAccounts();

    }

}
