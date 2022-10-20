package com.oaktwister.views.controls;

import com.oaktwister.core.ViewHandler;
import com.oaktwister.services.util.Resources;
import com.oaktwister.viewmodels.models.AccountViewModel;
import com.oaktwister.views.View;
import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import java.net.URL;
import java.util.ResourceBundle;

public class AccountPane extends StackPane implements View {

    private final ViewHandler viewHandler;
    private final AccountViewModel viewModel;

    @FXML private Button mainButton;
    @FXML private ImageView imageView;
    @FXML private Label platformNameLabel;
    @FXML private Label idLabel;
    @FXML private Label grantsCountLabel;
    @FXML private Label createdAtLabel;
    @FXML private Button deleteButton;

    public AccountPane(ViewHandler viewHandler, AccountViewModel viewModel) {
        super();
        this.viewHandler = viewHandler;
        this.viewModel = viewModel;
        viewHandler.loadCustomView(this);
    }

    @Override
    public String getViewLocation() {
        return Resources.Views.Controls.ACCOUNT_CELL;
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
