package com.oaktwister.views.identities;

import com.oaktwister.annotations.ViewDescriptor;
import com.oaktwister.core.ViewMediator;
import com.oaktwister.services.resources.ViewResources;
import com.oaktwister.viewmodels.pages.IdentitiesViewModel;
import com.oaktwister.viewmodels.models.IdentityViewModel;
import com.oaktwister.util.listeners.DualChangeListener;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.util.Iterator;
import java.util.Optional;
import java.util.ResourceBundle;

@ViewDescriptor(location = ViewResources.Identities.IDENTITIES_PANE)
public class IdentitiesPane extends AnchorPane implements Initializable {

    private final ViewMediator viewMediator;
    private final SimpleObjectProperty<IdentitiesViewModel> viewModelProperty;

    @FXML private ScrollPane scrollPane;
    @FXML private FlowPane flowPane;
    @FXML private Button addButton;

    public IdentitiesPane(ViewMediator viewMediator) {
        super();
        this.viewMediator = viewMediator;
        viewModelProperty = new SimpleObjectProperty<>();
        viewMediator.loadCustomView(this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        scrollPane.widthProperty().addListener((observable, oldValue, newValue) ->
                flowPane.setPrefWidth(newValue.doubleValue()));
        scrollPane.heightProperty().addListener((observable, oldValue, newValue) ->
                flowPane.setPrefHeight(newValue.doubleValue()));
    }

    public void setViewModel(@NotNull IdentitiesViewModel viewModel) {
        // TODO: Use weak properties listeners
        viewModelProperty.set(viewModel);
        viewModel.identitiesProperty().addListener(new DualChangeListener<IdentityViewModel>(
                this::onIdentityViewModelAdded, this::onIdentityViewModelRemoved));
        viewModel.loadIdentities();
    }

    public IdentitiesViewModel getViewModel() {
        return viewModelProperty.get();
    }

    public ReadOnlyObjectProperty<IdentitiesViewModel> viewModelProperty() {
        return viewModelProperty;
    }

    private void onIdentityViewModelAdded(IdentityViewModel identityViewModel) {

        // Bindings
        identityViewModel.onDeleteIdentityProperty().set(event -> {
            IdentityViewModel viewModel = event.getIdentityViewModel();
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Delete identity");
            alert.setContentText(String.format(
                    "Do you really want to delete identity %s?%n" +
                    "This action cannot be undone.",
                    viewModel.idProperty().get()));
            Optional<ButtonType> result = alert.showAndWait();
            if(result.isEmpty() || result.get().equals(ButtonType.CANCEL)) {
                event.cancel();
            }
            getViewModel().identitiesProperty().remove(viewModel);
        });

        // Get the IdentityPane from the viewHandler and add it to the flowPane's children
        IdentityPane identityPane = viewMediator.controls().getIdentityPane(identityViewModel);
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
