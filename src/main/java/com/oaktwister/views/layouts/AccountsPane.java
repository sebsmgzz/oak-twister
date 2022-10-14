package com.oaktwister.views.layouts;

import com.oaktwister.services.Resources;
import com.oaktwister.viewmodels.models.AccountViewModel;
import com.oaktwister.views.controls.AccountCell;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AccountsPane extends Pane implements Initializable {

    @FXML private Pane root;
    @FXML private Label titleLabel;
    @FXML private ListView<AccountViewModel> listView;
    @FXML private Button addButton;

    public AccountsPane() throws IOException {
        super();
        URL resourceUrl = AccountsPane.class.getResource(Resources.Views.Layouts.ACCOUNTS_PANE);
        FXMLLoader fxmlLoader = new FXMLLoader(resourceUrl);
        fxmlLoader.setRoot(this);
        fxmlLoader.setControllerFactory(aClass -> this);
        fxmlLoader.load();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        root.widthProperty().addListener(((observable, oldValue, newValue) ->
                listView.setPrefWidth(newValue.doubleValue())));
        root.heightProperty().addListener(((observable, oldValue, newValue) ->
                listView.setPrefHeight(newValue.doubleValue())));
        listView.setCellFactory(listView -> {
            try {
                return new AccountCell();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        // TODO: How is the AccountCell reading the AccountViewModel?
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

}
