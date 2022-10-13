package com.oaktwister.views.main;

import com.oaktwister.core.ViewFactory;
import com.oaktwister.viewmodels.main.PlatformsViewModel;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.ResourceBundle;

public class PlatformsViewController implements Initializable {

    private final ViewFactory viewFactory;
    private final PlatformsViewModel viewModel;
    private final SimpleListProperty<Node> platforms;

    @FXML
    public FlowPane flowPane;

    public PlatformsViewController(ViewFactory viewFactory, PlatformsViewModel viewModel) {
        this.viewFactory = viewFactory;
        this.viewModel = viewModel;
        platforms = new SimpleListProperty<>(FXCollections.observableArrayList());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        viewModel.loadPlatforms();
    }

    public void onAddPlatformClick(ActionEvent actionEvent) {
        viewModel.addPlatform("name", "image", "url");
    }

}
