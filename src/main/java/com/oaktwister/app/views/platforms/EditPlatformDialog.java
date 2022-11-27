package com.oaktwister.app.views.platforms;

import com.oaktwister.app.annotations.ViewDescriptor;
import com.oaktwister.app.core.UIContext;
import com.oaktwister.app.services.resources.ImageResources;
import com.oaktwister.app.services.resources.ViewResources;
import com.oaktwister.app.utils.extensions.FXMLUtil;
import com.oaktwister.app.viewmodels.models.claims.ClaimViewModel;
import com.oaktwister.app.viewmodels.models.PlatformViewModel;
import com.oaktwister.app.views.claims.ClaimsTable;
import com.oaktwister.app.views.claims.MetaGrantNamesComboBox;
import com.oaktwister.app.views.widgets.dialogs.DialogButton;
import com.oaktwister.app.views.widgets.dialogs.DialogFrame;
import com.oaktwister.app.views.widgets.dialogs.DialogResult;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

@ViewDescriptor(location = ViewResources.Platforms.EDIT_DIALOG)
public class EditPlatformDialog extends AnchorPane implements Initializable {

    private final static String TITLE = "Edit platform";

    private final UIContext ui;

    @FXML private DialogFrame dialogFrame;

    // Overview tab
    @FXML private TextField platformNameTextField;
    @FXML private TextField platformUrlTextField;
    @FXML private ImageView platformImageView;

    // Claims tab
    @FXML private TextField claimNameTextField;
    @FXML private MetaGrantNamesComboBox claimGrantTypeComboBox;
    @FXML private RadioButton claimOptionalRadioButton;
    @FXML private Button addClaimButton;
    @FXML private Button updateClaimButton;
    @FXML private Button removeClaimButton;
    @FXML private ClaimsTable claimsTable;

    private final SimpleObjectProperty<PlatformViewModel> platformProperty;

    public EditPlatformDialog(UIContext ui) {
        this.ui = ui;
        platformProperty = new SimpleObjectProperty<>();
        FXMLUtil.loadControl(this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dialogFrame.setTitle(TITLE);
        dialogFrame.setIcon(new Image(ImageResources.FontAwesome.LAYER_GROUP_SOLID));
        dialogFrame.addButtons(
                DialogButton.fromResult(DialogResult.SAVED),
                DialogButton.fromResult(DialogResult.CANCELED));
        platformProperty.addListener((observable, oldValue, newValue) -> {
            platformNameTextField.textProperty().bindBidirectional(newValue.nameProperty());
            platformUrlTextField.textProperty().bindBidirectional(newValue.urlProperty());
            platformImageView.imageProperty().bindBidirectional(newValue.imageProperty());
            claimsTable.claimsProperty().bind(newValue.claimMap().claimsProperty());
        });
        platformProperty.set(ui.viewModels().platform());
        claimsTable.selectedClaimProperty().addListener((observable, oldValue, newValue) -> {
            claimNameTextField.setText(newValue.getName());
            claimGrantTypeComboBox.setSelectedMetaGrantName(newValue.getMetaGrantName());
            claimOptionalRadioButton.setSelected(newValue.getIsOptional());
        });
        addClaimButton.setOnAction(this::onAddClaim);
        updateClaimButton.setOnAction(this::onUpdateClaim);
        removeClaimButton.setOnAction(this::onRemoveClaim);
    }

    public void showAndWait() {
        Stage stage = ui.navigation().getDialogStage(this);
        stage.getIcons().add(new Image(ImageResources.FontAwesome.LAYER_GROUP_SOLID));
        dialogFrame.showAndWait(stage);
    }

    private void onAddClaim(ActionEvent actionEvent) {
        try {
            PlatformViewModel platform = getPlatform();
            ClaimViewModel claim = ui.viewModels().claim();
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

    public ReadOnlyObjectProperty<DialogResult> resultProperty() {
        return dialogFrame.resultProperty();
    }
    public DialogResult getResult() {
        return resultProperty().get();
    }

    public ReadOnlyObjectProperty<PlatformViewModel> platformProperty() {
        return platformProperty;
    }
    public PlatformViewModel getPlatform() {
        return platformProperty().get();
    }

}
