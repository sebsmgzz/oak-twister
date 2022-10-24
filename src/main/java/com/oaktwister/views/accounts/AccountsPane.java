package com.oaktwister.views.accounts;

import com.oaktwister.annotations.ViewDescriptor;
import com.oaktwister.core.ViewMediator;
import com.oaktwister.services.resources.ViewResources;
import com.oaktwister.viewmodels.models.AccountViewModel;
import com.oaktwister.viewmodels.pages.AccountsViewModel;
import com.oaktwister.util.listeners.DualChangeListener;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.Iterator;
import java.util.Optional;
import java.util.ResourceBundle;

@ViewDescriptor(location = ViewResources.Accounts.ACCOUNTS_PANE)
public class AccountsPane extends AnchorPane implements Initializable {

    private final ViewMediator viewMediator;
    private final SimpleObjectProperty<AccountsViewModel> viewModelProperty;

    @FXML private Label titleLabel;
    @FXML private ScrollPane scrollPane;
    @FXML private FlowPane flowPane;
    @FXML private Button addButton;

    public AccountsPane(ViewMediator viewMediator) {
        super();
        this.viewMediator = viewMediator;
        this.viewModelProperty = new SimpleObjectProperty<>();
        viewMediator.loadCustomView(this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        scrollPane.widthProperty().addListener((observable, oldValue, newValue) ->
                flowPane.setPrefWidth(newValue.doubleValue()));
        scrollPane.heightProperty().addListener((observable, oldValue, newValue) ->
                flowPane.setPrefHeight(newValue.doubleValue()));
    }

    public void setViewModelProperty(AccountsViewModel viewModel) {
        viewModel.accountsProperty().addListener(new DualChangeListener<>(
                this::onAccountViewModelAdded, this::onAccountViewModelRemoved));
        viewModel.loadAccounts();
        viewModelProperty.set(viewModel);
    }

    public AccountsViewModel getViewModel() {
        return viewModelProperty.get();
    }

    public ReadOnlyObjectProperty<AccountsViewModel> viewModelProperty() {
        return viewModelProperty;
    }

    private void onAccountViewModelAdded(AccountViewModel identityViewModel) {

        // Bindings
        identityViewModel.onDeleteAccountProperty().set(event -> {
            AccountViewModel viewModel = event.getAccountViewModel();
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Delete account");
            alert.setContentText(String.format(
                    "Do you really want to delete account %s?%n" +
                            "This action cannot be undone.",
                    viewModel.idProperty().get()));
            Optional<ButtonType> result = alert.showAndWait();
            if(result.isEmpty() || result.get().equals(ButtonType.CANCEL)) {
                event.cancel();
            }
            viewModelProperty.get().accountsProperty().remove(viewModel);
        });

        // Get the AccountBox from the viewHandler and add it to the flowPane's children
        AccountPane accountPane = viewMediator.controls().getAccountPane(identityViewModel);
        accountPane.onMainActionProperty().set(event -> {
            // TODO: Show editable form for the identity
        });
        flowPane.getChildren().add(accountPane);

    }

    private void onAccountViewModelRemoved(AccountViewModel accountViewModel) {
        // Iterate through the flowPane's children
        Iterator<Node> iterator = flowPane.getChildren().iterator();
        while (iterator.hasNext()) {
            Node node = iterator.next();

            // Each flowPane child should be of type IdentityPane
            AccountPane accountPane = node instanceof AccountPane ? (AccountPane) node : null;
            if(accountPane == null) {
                throw new RuntimeException(
                        "A AccountsPane::flowPane children was found not to be an instance of AccountBox. " +
                        "This is not the expected behaviour. Something is critically wrong.");
            }

            // If the accountBox's AccountViewModel matches the one been removed, remove it as well
            AccountViewModel foundViewModel = accountPane.getViewModel();
            if (accountViewModel == foundViewModel) {
                iterator.remove();
            }

        }
    }

}
