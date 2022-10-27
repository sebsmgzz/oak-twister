package com.oaktwister.views.dialogs.platforms;

import com.oaktwister.annotations.ViewDescriptor;
import com.oaktwister.models.claims.Claim;
import com.oaktwister.services.resources.ViewResources;
import com.oaktwister.utils.extensions.NodeUtil;
import com.oaktwister.viewmodels.models.PlatformViewModel;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

@ViewDescriptor(location = ViewResources.Dialogs.EDIT_PLATFORM)
public class EditPlatformDialogPane extends DialogPane implements Initializable {

    private PlatformViewModel viewModel;

    @FXML private AnchorPane anchorPane;
    @FXML private TextField nameTextField;
    @FXML private TextField urlTextField;
    @FXML private ImageView imageView;
    @FXML private TableView<Claim> claimsTableView;
    @FXML private TableColumn<Claim, String> nameColumn;
    @FXML private TableColumn<Claim, String> grantTypeColumn;
    @FXML private TableColumn<Claim, Boolean> optionalColumn;

    public EditPlatformDialogPane() {
        NodeUtil.loadControl(this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<ButtonType> buttonTypes = super.getButtonTypes();
        buttonTypes.add(ButtonType.CLOSE);
        buttonTypes.add(ButtonType.FINISH);
    }

    public void setViewModel(PlatformViewModel viewModel) {
        this.viewModel = viewModel;

        nameTextField.textProperty().bindBidirectional(viewModel.nameProperty());
        urlTextField.textProperty().bindBidirectional(viewModel.urlProperty());
        imageView.imageProperty().bindBidirectional(viewModel.imageProperty());

        claimsTableView.itemsProperty().bind(viewModel.claims().claimsProperty());

        nameColumn.setCellValueFactory(cell -> {
            Claim claim = cell.getValue();
            String name = claim.getName();
            SimpleStringProperty nameProperty = new SimpleStringProperty(name);
            nameProperty.addListener((observable, oldValue, newValue) -> claim.setName(newValue));
            return nameProperty;
        });
        grantTypeColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getGrantType().getName()));
        optionalColumn.setCellValueFactory(cell -> new SimpleBooleanProperty(cell.getValue().getIsOptional()));

    }

    public PlatformViewModel getViewModel() {
        return viewModel;
    }

}
