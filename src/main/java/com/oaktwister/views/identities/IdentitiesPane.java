package com.oaktwister.views.identities;

import com.oaktwister.annotations.ViewDescriptor;
import com.oaktwister.core.ViewMediator;
import com.oaktwister.services.resources.ViewResources;
import com.oaktwister.utils.listeners.*;
import com.oaktwister.viewmodels.models.IdentityViewModel;
import com.oaktwister.viewmodels.collections.IdentitiesViewModel;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import java.net.URL;
import java.util.ResourceBundle;

@ViewDescriptor(location = ViewResources.Identities.IDENTITIES_PANE)
public class IdentitiesPane extends AnchorPane implements Initializable {

    private final ViewMediator viewMediator;

    private final SimpleObjectProperty<IdentitiesViewModel> viewModelProperty;
    private final SimpleMapProperty<IdentityViewModel, IdentityPane> identityPanesProperty;

    @FXML private Label titleLabel;
    @FXML private ScrollPane scrollPane;
    @FXML private FlowPane flowPane;
    @FXML private Button addButton;

    public IdentitiesPane(ViewMediator viewMediator) {
        super();
        this.viewMediator = viewMediator;
        viewModelProperty = new SimpleObjectProperty<>();
        identityPanesProperty = new SimpleMapProperty<>(FXCollections.observableHashMap());
        viewMediator.loadViewControl(this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // UI
        scrollPane.widthProperty().addListener((observable, oldValue, newValue) ->
                flowPane.setPrefWidth(newValue.doubleValue()));
        scrollPane.heightProperty().addListener((observable, oldValue, newValue) ->
                flowPane.setPrefHeight(newValue.doubleValue()));
        addButton.setOnAction(this::onAddButtonClick);

        // Properties
        identityPanesProperty.addListener(new MapItemAddedListener<>((identityViewModel, identityPane) -> {
            flowPane.getChildren().add(identityPane);
        }));
        identityPanesProperty.addListener(new MapItemRemovedListener<>((identityViewModel, identityPane) -> {
            flowPane.getChildren().remove(identityPane);
        }));

    }

    public void setViewModel(IdentitiesViewModel viewModel) {
        viewModel.identitiesProperty().addListener(new ListItemAddedListener<>(identityViewModel -> {
            IdentityPane identityPane = viewMediator.controlFactory.getIdentityPane(identityViewModel);
            identityPanesProperty.get().put(identityViewModel, identityPane);
        }));
        viewModel.identitiesProperty().addListener(new ListItemRemovedListener<>(identityViewModel -> {
            identityPanesProperty.get().remove(identityViewModel);
        }));
        viewModelProperty.set(viewModel);
    }

    public IdentitiesViewModel getViewModel() {
        return viewModelProperty.get();
    }

    public ReadOnlyObjectProperty<IdentitiesViewModel> viewModelProperty() {
        return viewModelProperty;
    }

    public StringProperty titleProperty() {
        return titleLabel.textProperty();
    }

    private void onAddButtonClick(ActionEvent event) {
        // TODO: IdentitiesPane::onAddButtonClick
    }

}
