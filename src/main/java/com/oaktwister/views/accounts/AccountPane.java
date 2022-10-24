package com.oaktwister.views.accounts;

import com.oaktwister.annotations.ViewDescriptor;
import com.oaktwister.core.ViewMediator;
import com.oaktwister.services.resources.ViewResources;
import com.oaktwister.viewmodels.models.AccountViewModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
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
    private final SimpleObjectProperty<AccountViewModel> viewModelProperty;

    @FXML private Button mainButton;
    @FXML private ImageView imageView;
    @FXML private Label platformNameLabel;
    @FXML private Label idLabel;
    @FXML private Label grantsCountLabel;
    @FXML private Label createdAtLabel;
    @FXML private Button deleteButton;

    public AccountPane(ViewMediator viewMediator) {
        super();
        this.viewMediator = viewMediator;
        this.viewModelProperty = new SimpleObjectProperty<>();
        viewMediator.loadCustomView(this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setOnMouseEntered(event -> deleteButton.setVisible(true));
        setOnMouseExited(event -> deleteButton.setVisible(false));
        StackPane.setAlignment(deleteButton, Pos.TOP_RIGHT);
    }

    public void setViewModel(AccountViewModel viewModel) {
        viewModel.idProperty().addListener((observable, oldValue, newValue) ->
                idLabel.setText(newValue.toString()));
        viewModel.platform().imageProperty().addListener((observable, oldValue, newValue) ->
                imageView.setImage(newValue));
        viewModel.grants().grantCountProperty().addListener((observable, oldValue, newValue) ->
                grantsCountLabel.setText(String.valueOf(newValue.intValue())));
        viewModel.createdAtProperty().addListener((observable, oldValue, newValue) ->
                createdAtLabel.setText(getViewModel().formatDate(newValue)));
        deleteButton.setOnAction(event -> getViewModel().delete());
        viewModelProperty.set(viewModel);
    }

    public AccountViewModel getViewModel() {
        return viewModelProperty.get();
    }

    public ReadOnlyObjectProperty<AccountViewModel> viewModelProperty() {
        return viewModelProperty;
    }

    public ObjectProperty<EventHandler<ActionEvent>> onMainActionProperty() {
        return mainButton.onActionProperty();
    }

}
