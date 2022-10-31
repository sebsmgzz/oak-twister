package com.oaktwister.controllers.dialogs;

import com.oaktwister.annotations.ViewDescriptor;
import com.oaktwister.services.resources.ViewResources;
import com.oaktwister.viewmodels.models.ClaimViewModel;
import com.oaktwister.viewmodels.models.PlatformViewModel;

import com.oaktwister.views.dialogs.EditPlatformDialogResult;
import javafx.beans.property.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

@ViewDescriptor(location = ViewResources.Dialogs.EDIT_PLATFORM)
public class EditPlatformDialogController implements Initializable {

    private final Stage stage;
    private final SimpleObjectProperty<PlatformViewModel> viewModelProperty;
    private final SimpleObjectProperty<EditPlatformDialogResult> resultProperty;

    @FXML private TextField nameTextField;
    @FXML private TextField urlTextField;
    @FXML private ImageView imageView;
    @FXML private TableView<ClaimViewModel> claimsTableView;
    @FXML private TableColumn<ClaimViewModel, String> nameColumn;
    @FXML private TableColumn<ClaimViewModel, String> grantTypeColumn;
    @FXML private TableColumn<ClaimViewModel, Boolean> optionalColumn;
    @FXML private Button saveButton;
    @FXML private Button cancelButton;

    public EditPlatformDialogController(Stage stage, PlatformViewModel viewModel) {
        this.stage = stage;
        viewModelProperty = new SimpleObjectProperty<>(viewModel);
        resultProperty = new SimpleObjectProperty<>(EditPlatformDialogResult.CANCELED);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        PlatformViewModel viewModel = viewModelProperty.get();

        nameTextField.textProperty().bindBidirectional(viewModel.nameProperty());
        urlTextField.textProperty().bindBidirectional(viewModel.urlProperty());
        imageView.imageProperty().bindBidirectional(viewModel.imageProperty());
        claimsTableView.itemsProperty().bind(viewModel.claimMap().claimsProperty());

        nameColumn.setCellValueFactory(cell -> cell.getValue().nameProperty());
        grantTypeColumn.setCellValueFactory(cell -> cell.getValue().grantTypeNameProperty());
        optionalColumn.setCellValueFactory(cell -> cell.getValue().isOptionalProperty());

        saveButton.setOnAction(event -> {
            resultProperty.set(EditPlatformDialogResult.SAVED);
            stage.close();
        });
        cancelButton.setOnAction(event -> {
            resultProperty.set(EditPlatformDialogResult.CANCELED);
            stage.close();
        });

    }

    public ReadOnlyObjectProperty<PlatformViewModel> viewModelProperty() {
        return viewModelProperty;
    }

    public ReadOnlyObjectProperty<EditPlatformDialogResult> resultProperty() {
        return resultProperty;
    }

}
