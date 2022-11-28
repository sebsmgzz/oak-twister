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
import javafx.beans.value.ObservableValue;
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
        claimsTable.selectedClaimProperty().addListener(this::onSelectedClaimChanged);
        addClaimButton.setOnAction(this::onAddClaim);
        updateClaimButton.setOnAction(this::onUpdateClaim);
        removeClaimButton.setOnAction(this::onRemoveClaim);
        platformProperty.addListener((observable, oldValue, newValue) -> {
            claimsTable.claimsProperty().bind(newValue.claimMap().claimsProperty());
        });
    }

    private void onAddClaim(ActionEvent actionEvent) {
        try {
            ClaimViewModel claim = getClaimFactory().call();
            claim.setName(claimNameTextField.getText());
            claim.setMetaGrantName(claimGrantTypeComboBox.getSelectedMetaGrantName());
            claim.setIsOptional(claimOptionalRadioButton.isSelected());
            PlatformViewModel platform = getPlatform();
            platform.claimMap().addClaim(claim);
        } catch (Exception ex) {
            // TODO: Handle exception
            throw new RuntimeException(ex);
        }
    }

    private void onUpdateClaim(ActionEvent actionEvent) {
        ClaimViewModel selectedClaim = claimsTable.getSelectedClaimProperty();
        if(selectedClaim != null) {
            selectedClaim.setName(claimNameTextField.getText());
            selectedClaim.setMetaGrantName(claimGrantTypeComboBox.getSelectedMetaGrantName());
            selectedClaim.setIsOptional(claimOptionalRadioButton.isSelected());
        } else {
            // TODO: Throw exception? This should never happen
        }
    }

    private void onRemoveClaim(ActionEvent actionEvent) {
        ClaimViewModel selectedClaim = claimsTable.getSelectedClaimProperty();
        if(selectedClaim != null) {
            getPlatform().claimMap().removeClaim(selectedClaim);
        } else {
            // TODO: Throw exception? This should never happen
        }
    }

    private void onSelectedClaimChanged(ObservableValue<? extends ClaimViewModel> observable,
                                        ClaimViewModel oldValue, ClaimViewModel newValue) {
        if(newValue != null) {
            claimNameTextField.setText(newValue.getName());
            claimGrantTypeComboBox.setSelectedMetaGrantName(newValue.getMetaGrantName());
            claimOptionalRadioButton.setSelected(newValue.getIsOptional());
            removeClaimButton.setDisable(false);
            updateClaimButton.setDisable(false);
        } else {
            removeClaimButton.setDisable(true);
            updateClaimButton.setDisable(true);
        }
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

}
