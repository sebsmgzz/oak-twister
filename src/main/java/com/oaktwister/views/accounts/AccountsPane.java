package com.oaktwister.views.accounts;

import com.oaktwister.annotations.ViewDescriptor;
import com.oaktwister.core.ViewMediator;
import com.oaktwister.services.resources.ViewResources;
import com.oaktwister.util.listeners.ListItemAddedListener;
import com.oaktwister.util.listeners.ListItemRemovedListener;
import com.oaktwister.util.listeners.MapItemAddedListener;
import com.oaktwister.util.listeners.MapItemRemovedListener;
import com.oaktwister.viewmodels.models.AccountViewModel;
import com.oaktwister.viewmodels.pages.AccountsViewModel;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import java.net.URL;
import java.util.ResourceBundle;

@ViewDescriptor(location = ViewResources.Accounts.ACCOUNTS_PANE)
public class AccountsPane extends AnchorPane implements Initializable {

    private final ViewMediator viewMediator;
    private final SimpleObjectProperty<AccountsViewModel> viewModelProperty;
    private final SimpleMapProperty<AccountViewModel, AccountPane> accountPanesProperty;

    @FXML private Label titleLabel;
    @FXML private ScrollPane scrollPane;
    @FXML private FlowPane flowPane;
    @FXML private Button addButton;

    public AccountsPane(ViewMediator viewMediator) {
        super();
        this.viewMediator = viewMediator;
        viewModelProperty = new SimpleObjectProperty<>();
        accountPanesProperty = new SimpleMapProperty<>(FXCollections.observableHashMap());
        viewMediator.loadViewControl(this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // UI
        scrollPane.widthProperty().addListener((observable, oldValue, newValue) ->
                flowPane.setPrefWidth(newValue.doubleValue()));
        scrollPane.heightProperty().addListener((observable, oldValue, newValue) ->
                flowPane.setPrefHeight(newValue.doubleValue()));
        addButton.setOnAction(this::onAddButtonClick);

        // Properties
        accountPanesProperty.addListener(new MapItemAddedListener<>((accountViewModel, accountPane) -> {
            flowPane.getChildren().add(accountPane);
        }));
        accountPanesProperty.addListener(new MapItemRemovedListener<>((accountViewModel, accountPane) -> {
            flowPane.getChildren().remove(accountPane);
        }));

    }

    public void setViewModel(AccountsViewModel viewModel) {
        viewModel.accountsProperty().addListener(new ListItemAddedListener<>(accountViewModel -> {
            AccountPane accountPane = viewMediator.controls().getAccountPane(accountViewModel);
            accountPanesProperty.put(accountViewModel, accountPane);
        }));
        viewModel.accountsProperty().addListener(new ListItemRemovedListener<>(accountViewModel -> {
            accountPanesProperty.remove(accountViewModel);
        }));
        viewModelProperty.set(viewModel);
    }

    public AccountsViewModel getViewModel() {
        return viewModelProperty.get();
    }

    public ReadOnlyObjectProperty<AccountsViewModel> viewModelProperty() {
        return viewModelProperty;
    }

    public StringProperty titleProperty() {
        return titleLabel.textProperty();
    }

    public void onAddButtonClick(ActionEvent event) {
        // TODO: AccountsPane::onAddButtonClick
    }

}
