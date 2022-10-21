package com.oaktwister.views.identities;

import com.oaktwister.annotations.ViewDescriptor;
import com.oaktwister.core.ViewHandler;
import com.oaktwister.services.resources.ViewResources;
import com.oaktwister.viewmodels.pages.IdentitiesViewModel;
import com.oaktwister.viewmodels.models.IdentityViewModel;
import com.oaktwister.util.listeners.DualChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.Iterator;
import java.util.Optional;
import java.util.ResourceBundle;

@ViewDescriptor(location = ViewResources.Identities.IDENTITIES_PANE)
public class IdentitiesPane extends AnchorPane implements Initializable {

    private final ViewHandler viewHandler;
    private final IdentitiesViewModel viewModel;

    @FXML private ScrollPane scrollPane;
    @FXML private FlowPane flowPane;
    @FXML private Button addButton;

    public IdentitiesPane(ViewHandler viewHandler, IdentitiesViewModel viewModel) {
        super();
        this.viewHandler = viewHandler;
        this.viewModel = viewModel;
        viewHandler.loadCustomView(this);
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
            Optional<ButtonType> result = alert.showAndWait();
            if(result.isEmpty() || result.get().equals(ButtonType.CANCEL)) {
                event.cancel();
            }
            this.viewModel.identitiesProperty().remove(viewModel);
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
        Iterator<Node> childrenIterator = flowPane.getChildren().iterator();
        while(childrenIterator.hasNext()) {
            Node node = childrenIterator.next();

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
                childrenIterator.remove();
            }

        }
    }

}
