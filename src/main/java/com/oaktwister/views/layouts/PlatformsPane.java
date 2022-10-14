package com.oaktwister.views.layouts;

import com.oaktwister.services.Resources;
import com.oaktwister.viewmodels.collections.PlatformsViewModel;
import com.oaktwister.views.controls.PlatformPane;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PlatformsPane extends VBox implements Initializable {

    private final PlatformsViewModel viewModel;

    @FXML private Label titleLabel;
    @FXML private FlowPane flowPane;
    @FXML private ScrollPane scrollPane;
    @FXML private Button addButton;

    private final SimpleListProperty<PlatformPane> platforms;

    public PlatformsPane(PlatformsViewModel viewModel) throws IOException {
        super();
        this.viewModel = viewModel;
        platforms = new SimpleListProperty<>(FXCollections.observableArrayList());
        URL resourceUrl = PlatformsPane.class.getResource(Resources.Views.Layouts.PLATFORMS_PANE);
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

        addButton.setOnAction(this::onAddButtonClick);

        platforms.addListener((ListChangeListener<PlatformPane>) change -> {
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

    private void onAddButtonClick(ActionEvent actionEvent) {
        // TODO: Add PlatformPane to the platforms field
    }

}
