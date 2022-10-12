package com.oaktwister.views.landing;

import com.oaktwister.core.ViewFactory;
import com.oaktwister.models.Drive;
import com.oaktwister.viewmodels.landing.LandingViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LandingViewController implements Initializable {

    private final ViewFactory viewFactory;
    private final LandingViewModel viewModel;

    @FXML
    private TableView<DriveViewModel> tableView;
    @FXML
    private TableColumn<DriveViewModel, String> nameColumn;
    @FXML
    private TableColumn<DriveViewModel, String> pathColumn;
    @FXML
    private TableColumn<DriveViewModel, String> fileSystemColumn;
    @FXML
    private TableColumn<DriveViewModel, Number> capacityColumn;
    @FXML
    private TableColumn<DriveViewModel, Number> spaceColumn;

    public LandingViewController(ViewFactory viewFactory, LandingViewModel viewModel) {
        this.viewFactory = viewFactory;
        this.viewModel = viewModel;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        viewModel.loadDrives();
        nameColumn.setCellValueFactory(c -> c.getValue().nameProperty());
        pathColumn.setCellValueFactory(c -> c.getValue().pathProperty());
        fileSystemColumn.setCellValueFactory(c -> c.getValue().fileSystemProperty());
        capacityColumn.setCellValueFactory(c -> c.getValue().capacityProperty());
        spaceColumn.setCellValueFactory(c -> c.getValue().spaceProperty());
        tableView.itemsProperty().bindBidirectional(viewModel.drivesProperty());
    }

    @FXML
    public void onLoadButtonClick(ActionEvent actionEvent) throws IOException {
        viewFactory.showMainView();
    }

    @FXML
    public void onRefreshButtonClick(ActionEvent actionEvent) throws IOException {
        viewModel.loadDrives();
    }

}
