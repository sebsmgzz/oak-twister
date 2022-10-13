package com.oaktwister.views.landing;

import com.oaktwister.core.ViewFactory;
import com.oaktwister.viewmodels.models.DriveViewModel;
import com.oaktwister.viewmodels.landing.LandingViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

public class LandingViewController implements Initializable {

    private final ViewFactory viewFactory;
    private final LandingViewModel viewModel;

    @FXML
    private TableView<DriveViewModel> tableView;
    @FXML
    private TableColumn<DriveViewModel, UUID> idColumn;
    @FXML
    private TableColumn<DriveViewModel, String> pathColumn;
    @FXML
    private TableColumn<DriveViewModel, String> capacityColumn;
    @FXML
    private TableColumn<DriveViewModel, String> spaceColumn;

    public LandingViewController(ViewFactory viewFactory, LandingViewModel viewModel) {
        this.viewFactory = viewFactory;
        this.viewModel = viewModel;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        viewModel.loadDrives();
        idColumn.setCellValueFactory(c -> c.getValue().idProperty());
        pathColumn.setCellValueFactory(c -> c.getValue().pathProperty());
        capacityColumn.setCellValueFactory(c -> c.getValue().capacityProperty());
        spaceColumn.setCellValueFactory(c -> c.getValue().spaceProperty());
        tableView.itemsProperty().bindBidirectional(viewModel.drivesProperty());
        tableView.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> viewModel.setSelectedDrive(newValue));
    }

    @FXML
    public void onLoadButtonClick(ActionEvent actionEvent) {
        try {
            viewModel.loadContext();
            viewFactory.showMainView();
        } catch (IllegalArgumentException ex) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error loading context");
            alert.setContentText(ex.getMessage());
            // TODO: Include options to format the drive in the alert's buttons
            alert.show();
        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("UI error");
            alert.setContentText(ex.getMessage());
            alert.show();
        }
    }

    @FXML
    public void onRefreshButtonClick(ActionEvent actionEvent) throws IOException {
        viewModel.loadDrives();
    }

}
