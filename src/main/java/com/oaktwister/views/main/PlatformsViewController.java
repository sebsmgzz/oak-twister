package com.oaktwister.views.main;

import com.oaktwister.core.ViewFactory;
import com.oaktwister.viewmodels.main.PlatformsViewModel;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class PlatformsViewController implements Initializable {

    private final ViewFactory viewFactory;
    private final PlatformsViewModel viewModel;

    public PlatformsViewController(ViewFactory viewFactory, PlatformsViewModel viewModel) {
        this.viewFactory = viewFactory;
        this.viewModel = viewModel;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

}
