package com.oaktwister.views.layouts;

import com.oaktwister.core.ViewHandler;
import com.oaktwister.services.Resources;
import com.oaktwister.viewmodels.collections.IdentitiesViewModel;
import com.oaktwister.viewmodels.models.IdentityViewModel;
import com.oaktwister.views.View;
import com.oaktwister.views.controls.IdentityPane;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class IdentitiesPane extends AnchorPane implements View {

    private final ViewHandler viewHandler;
    private final IdentitiesViewModel viewModel;

    @FXML private Label titleLabel;
    @FXML private AnchorPane anchorPane;
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
    public String getViewLocation() {
        return Resources.Views.Layouts.IDENTITIES_PANE;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        scrollPane.widthProperty().addListener((observable, oldValue, newValue) ->
                flowPane.setPrefWidth(newValue.doubleValue()));
        scrollPane.heightProperty().addListener((observable, oldValue, newValue) ->
                flowPane.setPrefHeight(newValue.doubleValue()));

        // Bindings
        viewModel.identitiesProperty().addListener(this::onIdentitiesChange);

        // Data loaders
        viewModel.loadIdentities();

    }

    private void onIdentitiesChange(ListChangeListener.Change<? extends IdentityViewModel> change) {
        List<Node> children = flowPane.getChildren();
        while (change.next()) {

            // When an identity is added, simply get the IdentityPane from the viewFactory and
            // add it to the flowPane's children
            if (change.wasAdded()) {
                for (IdentityViewModel identityViewModel : change.getAddedSubList()) {
                    IdentityPane identityPane = viewHandler.getIdentityPane(identityViewModel);
                    identityPane.onCloseProperty().set(event -> {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Delete identity");
                        alert.setContentText(String.format(
                            "Do you really want to delete identity %s?%n" +
                            "This action cannot be undone.",
                            identityPane.identifierProperty().get().toString()));
                        alert.show();
                    });
                    children.add(identityPane);
                }
            }

            // When a platform is removed, we need to iterate through the flowPane's children to
            // backtrack the Node to the IdentityViewModel been removed
            if (change.wasRemoved()) {
                for (IdentityViewModel identityViewModel : change.getAddedSubList()) {
                    for (Node node : children) {
                        IdentityPane identityPane = node instanceof IdentityPane? (IdentityPane) node : null;
                        if(identityPane == null) {
                            throw new RuntimeException(
                                "A IdentitiesPane::flowPane children was found not to be an instance of IdentityPane. " +
                                "This is not the expected behaviour. Something is critically wrong.");
                        }
                        IdentityViewModel foundViewModel = identityPane.getViewModel();
                        if (identityViewModel == foundViewModel) {
                            children.remove(node);
                        }
                    }
                }
            }

        }
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

    public ObjectProperty<EventHandler<ActionEvent>> onAddActionProperty() {
        return addButton.onActionProperty();
    }

    public EventHandler<ActionEvent> getOnAddAction() {
        return onAddActionProperty().get();
    }

    public void setOnAddAction(EventHandler<ActionEvent> onAddAction) {
        onAddActionProperty().set(onAddAction);
    }

}
