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
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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

    @FXML private DialogFrame dialogFrame;
    @FXML private TextField nameTextField;
    @FXML private TextField urlTextField;
    @FXML private ImageView imageView;
    @FXML private TableView<ClaimViewModel> claimsTableView;
    @FXML private TableColumn<ClaimViewModel, String> nameColumn;
    @FXML private TableColumn<ClaimViewModel, String> grantTypeColumn;
    @FXML private TableColumn<ClaimViewModel, Boolean> optionalColumn;

    private final SimpleObjectProperty<PlatformViewModel> platformProperty;
    private final SimpleObjectProperty<DialogResult> resultProperty;
    private final SimpleObjectProperty<Stage> stageProperty;

    public EditPlatformDialog() {
        platformProperty = new SimpleObjectProperty<>();
        resultProperty = new SimpleObjectProperty<>();
        stageProperty = new SimpleObjectProperty<>();
        FXMLUtil.loadControl(this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameColumn.setCellValueFactory(cell -> cell.getValue().nameProperty());
        grantTypeColumn.setCellValueFactory(cell -> cell.getValue().grantTypeNameProperty());
        optionalColumn.setCellValueFactory(cell -> cell.getValue().isOptionalProperty());
        platformProperty.addListener((observable, oldValue, newValue) -> {
            nameTextField.textProperty().bindBidirectional(newValue.nameProperty());
            urlTextField.textProperty().bindBidirectional(newValue.urlProperty());
            imageView.imageProperty().bindBidirectional(newValue.imageProperty());
            claimsTableView.itemsProperty().bind(newValue.claimMap().claimsProperty());
        });
        dialogFrame.setTitle(TITLE);
        dialogFrame.setIcon(new Image(ImageResources.FontAwesome.LAYER_GROUP_SOLID));
        dialogFrame.addButtons(
                DialogButton.fromResult(DialogResult.SAVED),
                DialogButton.fromResult(DialogResult.CANCELED));
    }

    public void showAndWait(Stage stage) {
        dialogFrame.showAndWait(stage);
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

    public ReadOnlyStringProperty nameProperty() {
        return nameTextField.textProperty();
    }
    public String getName() {
        return nameProperty().get();
    }

    public ReadOnlyStringProperty urlProperty() {
        return urlTextField.textProperty();
    }
    public String getUrl() {
        return urlProperty().get();
    }

    public ReadOnlyObjectProperty<Image> imageProperty() {
        return imageView.imageProperty();
    }
    public Image getImage() {
        return imageProperty().get();
    }

    public ReadOnlyObjectProperty<ObservableList<ClaimViewModel>> claimsProperty() {
        return claimsTableView.itemsProperty();
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
    public boolean removeClaims(ClaimViewModel... claims) {
        return getClaims().addAll(claims);
    }
    public void clearClaims() {
        getClaims().clear();
    }

}
