package com.oaktwister.views.controls;

import com.oaktwister.core.ViewHandler;
import com.oaktwister.services.util.Resources;
import com.oaktwister.viewmodels.pages.IdentitiesViewModel;
import com.oaktwister.viewmodels.models.IdentityViewModel;
import com.oaktwister.viewmodels.util.DualChangeListener;
import com.oaktwister.views.View;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class IdentitiesPane extends AnchorPane implements View {

    private final ViewHandler viewHandler;
    private final IdentitiesViewModel viewModel;

    @FXML private ScrollPane scrollPane;
    @FXML private FlowPane flowPane;

    public IdentitiesPane(ViewHandler viewHandler, IdentitiesViewModel viewModel) {
        super();
        this.viewHandler = viewHandler;
        this.viewModel = viewModel;
        viewHandler.loadCustomView(this);
    }

    @Override
    public String getViewLocation() {
        return Resources.Views.Layouts.IDENTITIES_PANE;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Styling
        scrollPane.widthProperty().addListener((observable, oldValue, newValue) ->
                flowPane.setPrefWidth(newValue.doubleValue()));
        scrollPane.heightProperty().addListener((observable, oldValue, newValue) ->
                flowPane.setPrefHeight(newValue.doubleValue()));

        // Property bindings
        // Update the flowPane children whenever a new identity is added or removed
        viewModel.identitiesProperty().addListener(new DualChangeListener<>(
                this::onIdentityViewModelAdded, this::onIdentityViewModelRemoved));

        // Load data
        viewModel.loadIdentities();

    }


    private void onIdentityViewModelAdded(IdentityViewModel identityViewModel) {

        // Bindings
        identityViewModel.onDeleteIdentityProperty().set(event -> {
            IdentityViewModel viewModel = event.getIdentityViewModel();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete identity");
            alert.setContentText(String.format(
                    "Do you really want to delete identity %s?%n" +
                    "This action cannot be undone.",
                    viewModel.idProperty().get()));
            alert.show();
        });

        // Get the IdentityPane from the viewHandler and add it to the flowPane's children
        IdentityPane identityPane = viewHandler.getIdentityPane(identityViewModel);
        identityPane.onMainActionProperty().set(event -> {
            // TODO: Show editable form for the identity
        });
        flowPane.getChildren().add(identityPane);

    }

    private void onIdentityViewModelRemoved(IdentityViewModel identityViewModel) {
        // Iterate through the flowPane's children
        List<Node> children = flowPane.getChildren();
        for (Node node : children) {

            // Each flowPane child should be of type IdentityPane
            IdentityPane identityPane = node instanceof IdentityPane? (IdentityPane) node : null;
            if(identityPane == null) {
                throw new RuntimeException(
                        "A IdentitiesPane::flowPane children was found not to be an instance of IdentityPane. " +
                        "This is not the expected behaviour. Something is critically wrong.");
            }

            // If the identityPane's IdentityViewModel matches the one been removed, remove it as well
            IdentityViewModel foundViewModel = identityPane.getViewModel();
            if (identityViewModel == foundViewModel) {
                children.remove(node);
            }

        }
    }

    @FXML
    public void onAddButtonClick(ActionEvent event) {
        // TODO: Add identity
    }

}