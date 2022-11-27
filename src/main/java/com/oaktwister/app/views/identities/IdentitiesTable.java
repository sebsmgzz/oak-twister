package com.oaktwister.app.views.identities;

import com.oaktwister.app.annotations.ViewDescriptor;
import com.oaktwister.app.services.resources.ViewResources;
import com.oaktwister.app.utils.extensions.FXMLUtil;
import com.oaktwister.app.utils.tables.CellValueFactory;
import com.oaktwister.app.viewmodels.models.IdentityViewModel;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.UUID;

@ViewDescriptor(location = ViewResources.Identities.TABLE)
public class IdentitiesTable extends TableView<IdentityViewModel> implements Initializable {

    // UI
    @FXML private TableColumn<IdentityViewModel, UUID> idColumn;
    @FXML private TableColumn<IdentityViewModel, String> nameColumn;
    @FXML private TableColumn<IdentityViewModel, Integer> grantsCountColumn;
    @FXML private TableColumn<IdentityViewModel, LocalDateTime> createdAtColumn;

    public IdentitiesTable() {
        FXMLUtil.loadControl(this);
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

}
