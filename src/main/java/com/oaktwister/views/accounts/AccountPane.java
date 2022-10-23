package com.oaktwister.views.accounts;

import com.oaktwister.annotations.ViewDescriptor;
import com.oaktwister.core.ViewMediator;
import com.oaktwister.services.resources.ViewResources;
import com.oaktwister.viewmodels.models.AccountViewModel;
import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import java.net.URL;
import java.util.ResourceBundle;

@ViewDescriptor(location = ViewResources.Accounts.ACCOUNT_PANE)
public class AccountPane extends StackPane implements Initializable {

    private final ViewMediator viewMediator;
    private final AccountViewModel viewModel;

    @FXML private Button mainButton;
    @FXML private ImageView imageView;
    @FXML private Label platformNameLabel;
    @FXML private Label idLabel;
    @FXML private Label grantsCountLabel;
    @FXML private Label createdAtLabel;
    @FXML private Button deleteButton;

    public AccountPane(ViewMediator viewMediator, AccountViewModel viewModel) {
        super();
        this.viewMediator = viewMediator;
        this.viewModel = viewModel;
        viewMediator.loadCustomView(this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Styling
        this.setOnMouseEntered(event -> deleteButton.setVisible(true));
        this.setOnMouseExited(event -> deleteButton.setVisible(false));
        StackPane.setAlignment(deleteButton, Pos.TOP_RIGHT);

        // Property bindings
        viewModel.idProperty().addListener((observable, oldValue, newValue) ->
                idLabel.setText(newValue.toString()));
        viewModel.platform().imageProperty().addListener((observable, oldValue, newValue) ->
                imageView.setImage(newValue));
        viewModel.grants().grantCountProperty().addListener((observable, oldValue, newValue) ->
                grantsCountLabel.setText(String.valueOf(newValue.intValue())));
        viewModel.createdAtProperty().addListener((observable, oldValue, newValue) ->
                createdAtLabel.textProperty().set(viewModel.formatDate(newValue)));
        deleteButton.onActionProperty().set(event -> viewModel.delete());

    }

    public AccountViewModel getViewModel() {
        return viewModel;
    }

    public ObjectProperty<EventHandler<ActionEvent>> onMainActionProperty() {
        return mainButton.onActionProperty();
    }

}
