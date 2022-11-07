package com.oaktwister.views.platforms;

import com.oaktwister.annotations.ViewDescriptor;
import com.oaktwister.services.resources.ViewResources;
import com.oaktwister.utils.extensions.NodeUtil;
import com.oaktwister.viewmodels.models.ClaimViewModel;

import com.oaktwister.viewmodels.models.PlatformViewModel;
import com.oaktwister.views.DialogResult;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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

    @FXML private TextField nameTextField;
    @FXML private TextField urlTextField;
    @FXML private ImageView imageView;
    @FXML private TableView<ClaimViewModel> claimsTableView;
    @FXML private TableColumn<ClaimViewModel, String> nameColumn;
    @FXML private TableColumn<ClaimViewModel, String> grantTypeColumn;
    @FXML private TableColumn<ClaimViewModel, Boolean> optionalColumn;
    @FXML private Button saveButton;
    @FXML private Button cancelButton;

    private final SimpleObjectProperty<PlatformViewModel> platformProperty;
    private final SimpleObjectProperty<DialogResult> resultProperty;
    private final SimpleObjectProperty<Stage> stageProperty;

    public EditPlatformDialog() {
        platformProperty = new SimpleObjectProperty<>();
        resultProperty = new SimpleObjectProperty<>();
        stageProperty = new SimpleObjectProperty<>();
        NodeUtil.loadControl(this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameColumn.setCellValueFactory(cell -> cell.getValue().nameProperty());
        grantTypeColumn.setCellValueFactory(cell -> cell.getValue().grantTypeNameProperty());
        optionalColumn.setCellValueFactory(cell -> cell.getValue().isOptionalProperty());
        saveButton.setOnAction(event -> {
            resultProperty.set(DialogResult.SAVED);
            Stage stage = stageProperty.get();
            if(stage != null) {
                stage.close();
            }
        });
        cancelButton.setOnAction(event -> {
            resultProperty.set(DialogResult.CANCELED);
            Stage stage = stageProperty.get();
            if(stage != null) {
                stage.close();
            }
        });
        platformProperty.addListener((observable, oldValue, newValue) -> {
            nameTextField.textProperty().bindBidirectional(newValue.nameProperty());
            urlTextField.textProperty().bindBidirectional(newValue.urlProperty());
            imageView.imageProperty().bindBidirectional(newValue.imageProperty());
            claimsTableView.itemsProperty().bind(newValue.claimMap().claimsProperty());
        });
    }

    public ReadOnlyObjectProperty<DialogResult> resultProperty() {
        return resultProperty;
    }

    public SimpleObjectProperty<Stage> stageProperty() {
        return stageProperty;
    }

    public SimpleObjectProperty<PlatformViewModel> platformProperty() {
        return platformProperty;
    }

    public ReadOnlyStringProperty nameProperty() {
        return nameTextField.textProperty();
    }

    public ReadOnlyStringProperty urlProperty() {
        return urlTextField.textProperty();
    }

    public ReadOnlyObjectProperty<Image> imageProperty() {
        return imageView.imageProperty();
    }

    public ReadOnlyObjectProperty<ObservableList<ClaimViewModel>> claimsProperty() {
        return claimsTableView.itemsProperty();
    }

}
