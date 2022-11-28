package com.oaktwister.app.views.platforms;

import com.oaktwister.app.annotations.ViewDescriptor;
import com.oaktwister.app.services.resources.ViewResources;
import com.oaktwister.app.utils.extensions.FXMLUtil;
import com.oaktwister.app.viewmodels.models.PlatformViewModel;
import com.oaktwister.app.viewmodels.models.claims.ClaimViewModel;
import com.oaktwister.app.views.claims.ClaimsTable;
import com.oaktwister.app.views.claims.MetaGrantNamesComboBox;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;

@ViewDescriptor(location = ViewResources.Platforms.EDIT_DIALOG_CLAIMS_TAB)
public class EditPlatformDialogClaimsTab extends GridPane implements Initializable {

    // UI
    @FXML private TextField claimNameTextField;
    @FXML private MetaGrantNamesComboBox claimGrantTypeComboBox;
    @FXML private RadioButton claimOptionalRadioButton;
    @FXML private Button addClaimButton;
    @FXML private Button updateClaimButton;
    @FXML private Button removeClaimButton;
    @FXML private ClaimsTable claimsTable;

    // Properties
    private final SimpleObjectProperty<PlatformViewModel> platformProperty;
    private final SimpleObjectProperty<Callable<ClaimViewModel>> claimFactoryProperty;

    public EditPlatformDialogClaimsTab() {
        platformProperty = new SimpleObjectProperty<>();
        claimFactoryProperty = new SimpleObjectProperty<>();
        FXMLUtil.loadControl(this);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        claimsTable.selectedClaimProperty().addListener((observable, oldValue, newValue) -> {
            claimNameTextField.setText(newValue.getName());
            claimGrantTypeComboBox.setSelectedMetaGrantName(newValue.getMetaGrantName());
            claimOptionalRadioButton.setSelected(newValue.getIsOptional());
        });
        addClaimButton.setOnAction(this::onAddClaim);
        updateClaimButton.setOnAction(this::onUpdateClaim);
        removeClaimButton.setOnAction(this::onRemoveClaim);
    }

    private void onAddClaim(ActionEvent actionEvent) {
        try {
            PlatformViewModel platform = getPlatform();
            ClaimViewModel claim = getClaimFactory().call();
            claim.setName(claimNameTextField.getText());
            claim.setMetaGrantName(claimGrantTypeComboBox.getSelectedMetaGrantName());
            claim.setIsOptional(claimOptionalRadioButton.isSelected());
            platform.claimMap().addClaim(claim);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private void onUpdateClaim(ActionEvent actionEvent) {
    }

    private void onRemoveClaim(ActionEvent actionEvent) {
    }

    public ObjectProperty<PlatformViewModel> platformProperty() {
        return platformProperty;
    }
    public PlatformViewModel getPlatform() {
        return platformProperty().get();
    }
    public void setPlatform(PlatformViewModel value) {
        platformProperty().set(value);
    }

    public ObjectProperty<Callable<ClaimViewModel>> claimFactoryProperty() {
        return claimFactoryProperty;
    }
    public Callable<ClaimViewModel> getClaimFactory() {
        return claimFactoryProperty().get();
    }
    public void setClaimFactory(Callable<ClaimViewModel> value) {
        claimFactoryProperty().set(value);
    }

    public ObjectProperty<ObservableList<ClaimViewModel>> claimsProperty() {
        return claimsTable.claimsProperty();
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
