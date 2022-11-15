package com.oaktwister.app.views.platforms;

import com.oaktwister.app.annotations.ViewDescriptor;
import com.oaktwister.app.services.resources.ImageResources;
import com.oaktwister.app.services.resources.ViewResources;
import com.oaktwister.app.utils.extensions.FXMLUtil;
import com.oaktwister.app.viewmodels.models.ClaimViewModel;
import com.oaktwister.app.viewmodels.models.PlatformViewModel;
import com.oaktwister.app.views.widgets.dialogs.DialogButton;
import com.oaktwister.app.views.widgets.dialogs.DialogFrame;
import com.oaktwister.app.views.widgets.dialogs.DialogResult;
import javafx.beans.property.ObjectProperty;
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

import java.lang.reflect.Array;
import java.net.URL;
import java.util.*;
import java.util.concurrent.Callable;

@ViewDescriptor(location = ViewResources.Platforms.EDIT_DIALOG)
public class EditPlatformDialog extends AnchorPane implements Initializable {

    private final static String TITLE = "Edit platform";

    @FXML private DialogFrame dialogFrame;

    // Overview tab
    @FXML private TextField platformNameTextField;
    @FXML private TextField platformUrlTextField;
    @FXML private ImageView platformImageView;

    // Claims tab
    @FXML private TextField claimNameTextField;
    @FXML private GrantTypesComboBox claimGrantTypeComboBox;
    @FXML private RadioButton claimOptionalRadioButton;
    @FXML private Button addClaimButton;
    @FXML private Button updateClaimButton;
    @FXML private Button removeClaimButton;
    @FXML private ClaimsTable claimsTable;

    private final SimpleObjectProperty<PlatformViewModel> platformProperty;
    private final SimpleObjectProperty<Callable<ClaimViewModel>> claimFactoryProperty;
    private final SimpleObjectProperty<Callable<Collection<String>>> grantTypesFactoryProperty;

    public EditPlatformDialog() {
        platformProperty = new SimpleObjectProperty<>();
        claimFactoryProperty = new SimpleObjectProperty<>();
        grantTypesFactoryProperty = new SimpleObjectProperty<>();
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
        claimGrantTypeComboBox.setOnShowing(event -> {
            try {
                Callable<Collection<String>> grantsFactory = grantTypesFactoryProperty.get();
                Collection<String> grants = grantsFactory.call();
                claimGrantTypeComboBox.clearGrantTypes();
                claimGrantTypeComboBox.addGrantTypes(grants.toArray(new String[0]));
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        addClaimButton.setOnAction(this::onAddClaim);
        updateClaimButton.setOnAction(this::onUpdateClaim);
        removeClaimButton.setOnAction(this::onRemoveClaim);
    }

    public void showAndWait(Stage stage) {
        stage.getIcons().add(new Image(ImageResources.FontAwesome.LAYER_GROUP_SOLID));
        dialogFrame.showAndWait(stage);
    }

    private void onAddClaim(ActionEvent actionEvent) {
        try {
            PlatformViewModel platform = getPlatform();
            ClaimViewModel claim = getClaimFactory().call();
            claim.setName(claimNameTextField.getText());
            claim.setGrantTypeName(claimGrantTypeComboBox.getSelectedGrantType());
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

    public ObjectProperty<Callable<Collection<String>>> grantTypesFactoryProperty() {
        return grantTypesFactoryProperty;
    }
    public Callable<Collection<String>> getGrantTypesFactory() {
        return grantTypesFactoryProperty().get();
    }
    public void setGrantTypesFactory(Callable<Collection<String>> value) {
        grantTypesFactoryProperty().set(value);
    }

}
