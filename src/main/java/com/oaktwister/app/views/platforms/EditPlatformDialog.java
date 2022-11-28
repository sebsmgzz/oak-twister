package com.oaktwister.app.views.platforms;

import com.oaktwister.app.annotations.ViewDescriptor;
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
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;

@ViewDescriptor(location = ViewResources.Platforms.EDIT_DIALOG)
public class EditPlatformDialog extends AnchorPane implements Initializable {

    private final static String TITLE = "Edit platform";

    // UI
    @FXML private DialogFrame dialogFrame;
    @FXML private EditPlatformDialogOverviewTab overviewTab;
    @FXML private EditPlatformDialogClaimsTab claimsTab;

    // Properties
    private final SimpleObjectProperty<PlatformViewModel> platformProperty;

    public EditPlatformDialog() {
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
            overviewTab.platformNameProperty().bindBidirectional(newValue.nameProperty());
            overviewTab.platformUrlProperty().bindBidirectional(newValue.urlProperty());
            overviewTab.platformImageProperty().bindBidirectional(newValue.imageProperty());
            claimsTab.claimsProperty().bind(newValue.claimMap().claimsProperty());
        });
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

}
