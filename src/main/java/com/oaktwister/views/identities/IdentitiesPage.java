package com.oaktwister.views.identities;

import com.oaktwister.annotations.ViewDescriptor;
import com.oaktwister.services.resources.ViewResources;
import com.oaktwister.utils.extensions.NodeUtil;
import com.oaktwister.utils.tables.CellValueFactory;
import com.oaktwister.viewmodels.models.IdentityViewModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.UUID;

@ViewDescriptor(location = ViewResources.Identities.PAGE)
public class IdentitiesPage extends AnchorPane implements Initializable {

    @FXML private Label titleLabel;
    @FXML private Button addButton;

    @FXML private TableView<IdentityViewModel> tableView;
    @FXML private TableColumn<IdentityViewModel, UUID> idColumn;
    @FXML private TableColumn<IdentityViewModel, String> nameColumn;
    @FXML private TableColumn<IdentityViewModel, Integer> grantsCountColumn;
    @FXML private TableColumn<IdentityViewModel, LocalDateTime> createdAtColumn;

    public IdentitiesPage() {
        NodeUtil.loadControl(this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idColumn.setCellValueFactory(cell -> cell.getValue().idProperty());
        nameColumn.setCellValueFactory(cell -> cell.getValue().nameProperty());
        createdAtColumn.setCellValueFactory(new CellValueFactory<>(identity ->
                identity.createdAtProperty().get()));
        grantsCountColumn.setCellValueFactory(new CellValueFactory<>(identity ->
                identity.grantMap().grantCountProperty().get()));
    }

    public StringProperty titleProperty() {
        return titleLabel.textProperty();
    }

    public ObjectProperty<EventHandler<ActionEvent>> onAddActionProperty() {
        return addButton.onActionProperty();
    }

    public ObjectProperty<ObservableList<IdentityViewModel>> identitiesProperty() {
        return tableView.itemsProperty();
    }

}
