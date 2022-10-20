package com.oaktwister.views.controls;

import com.oaktwister.core.ViewHandler;
import com.oaktwister.services.util.Resources;
import com.oaktwister.viewmodels.models.IdentityViewModel;
import com.oaktwister.views.View;
import javafx.beans.property.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class IdentityPane extends StackPane implements View {

    private final ViewHandler viewHandler;
    private final IdentityViewModel viewModel;

    @FXML private Button mainButton;
    @FXML private Label identifierLabel;
    @FXML private Label grantsLabel;
    @FXML private Label createdAtLabel;
    @FXML private Button deleteButton;

    public IdentityPane(ViewHandler viewHandler, IdentityViewModel viewModel) {
        super();
        this.viewHandler = viewHandler;
        this.viewModel = viewModel;
        viewHandler.loadCustomView(this);
    }

    @Override
    public String getViewLocation() {
        return Resources.Views.Controls.IDENTITY_CELL;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Styling
        this.setOnMouseEntered(event -> deleteButton.setVisible(true));
        this.setOnMouseExited(event -> deleteButton.setVisible(false));
        StackPane.setAlignment(deleteButton, Pos.TOP_RIGHT);

        // Property bindings
        viewModel.idProperty().addListener((observable, oldValue, newValue) ->
                identifierLabel.setText(newValue.toString()));
        viewModel.createdAtProperty().addListener((observable, oldValue, newValue) ->
                createdAtLabel.setText(viewModel.formatDate(newValue))); // TODO: Use date time formatter
        viewModel.grantMap().grantCountProperty().addListener((observable, oldValue, newValue) ->
                grantsLabel.setText(String.valueOf(newValue.intValue())));
        deleteButton.onActionProperty().set(event -> viewModel.delete());

    }

    public IdentityViewModel getViewModel() {
        return viewModel;
    }

    public ObjectProperty<EventHandler<ActionEvent>> onMainActionProperty() {
        return mainButton.onActionProperty();
    }

}
