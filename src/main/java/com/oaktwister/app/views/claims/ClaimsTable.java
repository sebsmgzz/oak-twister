package com.oaktwister.app.views.claims;

import com.oaktwister.app.annotations.ViewDescriptor;
import com.oaktwister.app.services.resources.ViewResources;
import com.oaktwister.app.utils.extensions.FXMLUtil;
import com.oaktwister.app.viewmodels.models.claims.ClaimViewModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

@ViewDescriptor(location = ViewResources.Claims.TABLE)
public class ClaimsTable extends AnchorPane implements Initializable {

    // UI
    @FXML private TableView<ClaimViewModel> tableView;
    @FXML private TableColumn<ClaimViewModel, String> nameColumn;
    @FXML private TableColumn<ClaimViewModel, String> metaGrantColumn;
    @FXML private TableColumn<ClaimViewModel, Boolean> optionalColumn;

    public ClaimsTable() {
        FXMLUtil.loadControl(this);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameColumn.setCellValueFactory(cell -> cell.getValue().nameProperty());
        metaGrantColumn.setCellValueFactory(cell -> cell.getValue().metaGrantNameProperty());
        optionalColumn.setCellValueFactory(cell -> cell.getValue().isOptionalProperty());
        tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    public ReadOnlyObjectProperty<ClaimViewModel> selectedClaimProperty() {
        return tableView.getSelectionModel().selectedItemProperty();
    }
    public ClaimViewModel getSelectedClaimProperty() {
        return selectedClaimProperty().get();
    }

    public ObjectProperty<ObservableList<ClaimViewModel>> claimsProperty() {
        return tableView.itemsProperty();
    }
    public ObservableList<ClaimViewModel> getClaims() {
        return claimsProperty().get();
    }
    public boolean addClaim(ClaimViewModel claim) {
        return getClaims().add(claim);
    }
    public boolean addClaims(ClaimViewModel... claims) {
        return getClaims().addAll(claims);
    }
    public boolean removeClaim(ClaimViewModel claim) {
        return getClaims().remove(claim);
    }
    public boolean removeClaims(ClaimViewModel claims) {
        return getClaims().removeAll(claims);
    }
    public void clearClaims() {
        getClaims().clear();
    }

}
