package com.oaktwister.views.landings;

import com.oaktwister.annotations.ViewDescriptor;
import com.oaktwister.core.ViewMediator;
import com.oaktwister.services.resources.ViewResources;
import com.oaktwister.viewmodels.models.DriveViewModel;
import com.oaktwister.viewmodels.roots.LandingViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

@ViewDescriptor(location = ViewResources.Landings.LANDING_VIEW)
public class LandingViewController implements Initializable {

    private final ViewMediator viewMediator;

    private final LandingViewModel viewModel;

    @FXML private TableView<DriveViewModel> tableView;
    @FXML private TableColumn<DriveViewModel, UUID> idColumn;
    @FXML private TableColumn<DriveViewModel, String> pathColumn;
    @FXML private TableColumn<DriveViewModel, String> capacityColumn;
    @FXML private TableColumn<DriveViewModel, String> spaceColumn;
    @FXML private Button loadButton;
    @FXML private Button refreshButton;

    public LandingViewController(ViewMediator viewMediator, LandingViewModel viewModel) {
        this.viewMediator = viewMediator;
        this.viewModel = viewModel;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Set cell's factories
        idColumn.setCellValueFactory(cell -> cell.getValue().idProperty());
        pathColumn.setCellValueFactory(cell -> cell.getValue().pathPropertyProperty());
        capacityColumn.setCellValueFactory(cell -> cell.getValue().capacityProperty().asString());
        spaceColumn.setCellValueFactory(cell -> cell.getValue().spaceProperty().asString());

        // Set the button's actions
        loadButton.setOnAction(this::onLoadButtonClick);
        refreshButton.setOnAction(this::onRefreshButtonClick);

        // Bind the tableView's properties with the viewModel
        tableView.itemsProperty().bind(viewModel.drivesProperty());
        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            viewModel.selectedDriveProperty().set(newValue);
        });

        // Load drives
        viewModel.loadDrives();

    }

    private void onLoadButtonClick(ActionEvent actionEvent) {
        try {
            viewModel.loadContext();
            viewMediator.showMainView();
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error loading context");
            alert.setContentText(ex.getMessage());
            // TODO: Include options to format the drive in the alert's buttons
            alert.show();
        }
    }

    private void onRefreshButtonClick(ActionEvent actionEvent) {
        viewModel.loadDrives();
    }

}
