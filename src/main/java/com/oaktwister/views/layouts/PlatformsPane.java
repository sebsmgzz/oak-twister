package com.oaktwister.views.layouts;

import com.oaktwister.services.Resources;
import com.oaktwister.views.controls.PlatformPane;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PlatformsPane extends VBox implements Initializable {

    @FXML private Label titleLabel;
    @FXML private FlowPane flowPane;
    @FXML private ScrollPane scrollPane;

    private final ObservableList<PlatformPane> platformPanes;

    public PlatformsPane() throws IOException {
        super();
        platformPanes = new SimpleListProperty<>(FXCollections.observableArrayList());
        URL resourceUrl = AccountsPane.class.getResource(Resources.Views.Layouts.PLATFORMS_PANE);
        FXMLLoader fxmlLoader = new FXMLLoader(resourceUrl);
        fxmlLoader.setRoot(this);
        fxmlLoader.setControllerFactory(aClass -> this);
        fxmlLoader.load();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.widthProperty().addListener(((observable, oldValue, newValue) ->
                scrollPane.setPrefWidth(newValue.doubleValue())));
        this.heightProperty().addListener(((observable, oldValue, newValue) ->
                scrollPane.setPrefHeight(newValue.doubleValue())));

        platformPanes.addListener((ListChangeListener<PlatformPane>) change -> {
            List<Node> children = flowPane.getChildren();
            while (change.next()) {
                if (change.wasAdded()) {
                    children.addAll(change.getAddedSubList());
                }
                if (change.wasRemoved()) {
                    children.removeAll(change.getRemoved());
                }
            }
        });

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

    public ObservableList<PlatformPane> getPlatformPanes() {
        return platformPanes;
    }

    public PlatformPane getPlatformPane(int index) {
        return getPlatformPanes().get(index);
    }

    public void addPlatformPane(PlatformPane platformPane) {
        getPlatformPanes().add(platformPane);
    }

    public void removePlatformPane(int index) {
        getPlatformPanes().remove(index);
    }

    public void removePlatformPane(PlatformPane platformPane) {
        getPlatformPanes().remove(platformPane);
    }

}
