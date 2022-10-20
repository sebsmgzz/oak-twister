package com.oaktwister.views.controls;

import com.oaktwister.core.ViewHandler;
import com.oaktwister.services.util.Resources;
import com.oaktwister.viewmodels.models.AccountViewModel;
import com.oaktwister.views.View;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class AccountBox extends HBox implements View {

    private final ViewHandler viewHandler;
    private final AccountViewModel viewModel;

    @FXML private ImageView imageView;
    @FXML private Label platformNameLabel;
    @FXML private Label idLabel;
    @FXML private Label grantsCountLabel;
    @FXML private Label createdAtLabel;

    public AccountBox(ViewHandler viewHandler, AccountViewModel viewModel) {
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
        viewModel.idProperty().addListener((observable, oldValue, newValue) ->
                idLabel.setText(newValue.toString()));
        viewModel.grants().grantCountProperty().addListener((observable, oldValue, newValue) ->
                grantsCountLabel.setText(String.valueOf(newValue.intValue())));
        viewModel.createdAtProperty().addListener((observable, oldValue, newValue) ->
                createdAtLabel.textProperty().set(newValue.toString())); // TODO: Use date time formatter
    }

}
