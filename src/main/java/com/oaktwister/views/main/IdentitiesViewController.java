package com.oaktwister.views.main;

import com.oaktwister.core.ViewFactory;
import com.oaktwister.viewmodels.main.IdentitiesViewModel;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class IdentitiesViewController implements Initializable {

    private final ViewFactory viewFactory;
    private final IdentitiesViewModel viewModel;

    public IdentitiesViewController(ViewFactory viewFactory, IdentitiesViewModel viewModel) {
        this.viewFactory = viewFactory;
        this.viewModel = viewModel;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

}
