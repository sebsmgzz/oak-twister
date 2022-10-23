package com.oaktwister.views.identities;

import com.oaktwister.annotations.ViewDescriptor;
import com.oaktwister.core.ViewMediator;
import com.oaktwister.services.resources.ViewResources;
import com.oaktwister.viewmodels.models.IdentityViewModel;
import javafx.beans.property.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

@ViewDescriptor(location = ViewResources.Identities.IDENTITY_PANE)
public class IdentityPane extends StackPane implements Initializable {

    private final ViewMediator viewMediator;

    @FXML private Button mainButton;
    @FXML private Label identifierLabel;
    @FXML private Label grantsLabel;
    @FXML private Label createdAtLabel;
    @FXML private Button deleteButton;

    private final SimpleObjectProperty<IdentityViewModel> viewModelProperty;

    public IdentityPane(ViewMediator viewMediator) {
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

    public ReadOnlyObjectProperty<IdentityViewModel> viewModelProperty() {
        return viewModelProperty;
    }

    public IdentityViewModel getViewModel() {
        return viewModelProperty.get();
    }

    public void setViewModel(IdentityViewModel viewModel) {
        // TODO: Use weak properties listeners
        viewModel.idProperty().addListener((observable, oldValue, newValue) ->
                identifierLabel.setText(newValue.toString()));
        viewModel.createdAtProperty().addListener((observable, oldValue, newValue) ->
                createdAtLabel.setText(viewModel.formatDate(newValue))); // TODO: Use date time formatter
        viewModel.grantMap().grantCountProperty().addListener((observable, oldValue, newValue) ->
                grantsLabel.setText(String.valueOf(newValue.intValue())));
        deleteButton.onActionProperty().set(event -> viewModel.delete());
    }

    public ObjectProperty<EventHandler<ActionEvent>> onMainActionProperty() {
        return mainButton.onActionProperty();
    }

}
