package com.oaktwister.views.dialogs;

import com.oaktwister.annotations.ViewDescriptor;
import com.oaktwister.services.resources.ViewResources;
import com.oaktwister.utils.extensions.NodeUtil;
import com.oaktwister.viewmodels.models.ClaimViewModel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
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

import java.net.URL;
import java.util.ResourceBundle;

@ViewDescriptor(location = ViewResources.Dialogs.EDIT_PLATFORM)
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

    private final SimpleObjectProperty<DialogResult> resultProperty;

    public EditPlatformDialog() {
        resultProperty = new SimpleObjectProperty<>();
        NodeUtil.loadControl(this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameColumn.setCellValueFactory(cell -> cell.getValue().nameProperty());
        grantTypeColumn.setCellValueFactory(cell -> cell.getValue().grantTypeNameProperty());
        optionalColumn.setCellValueFactory(cell -> cell.getValue().isOptionalProperty());
        saveButton.setOnAction(event -> resultProperty.set(DialogResult.SAVED));
        cancelButton.setOnAction(event -> resultProperty.set(DialogResult.CANCELED));
    }

    public StringProperty nameProperty() {
        return nameTextField.textProperty();
    }

    public StringProperty urlProperty() {
        return urlTextField.textProperty();
    }

    public ObjectProperty<Image> imageProperty() {
        return imageView.imageProperty();
    }

    public ObjectProperty<ObservableList<ClaimViewModel>> claimsProperty() {
        return claimsTableView.itemsProperty();
    }

    public ReadOnlyObjectProperty<DialogResult> resultProperty() {
        return resultProperty;
    }

}
