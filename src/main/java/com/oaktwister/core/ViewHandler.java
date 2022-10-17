package com.oaktwister.core;

import com.oaktwister.services.Resources;
import com.oaktwister.viewmodels.models.AccountViewModel;
import com.oaktwister.viewmodels.models.IdentityViewModel;
import com.oaktwister.viewmodels.models.PlatformViewModel;
import com.oaktwister.views.View;
import com.oaktwister.views.controls.AccountCell;
import com.oaktwister.views.controls.IdentityCell;
import com.oaktwister.views.controls.ImageButtonBox;
import com.oaktwister.views.controls.PlatformPane;
import com.oaktwister.views.layouts.AccountsPane;
import com.oaktwister.views.layouts.IdentitiesPane;
import com.oaktwister.views.layouts.PlatformsPane;
import com.oaktwister.views.roots.LandingViewController;
import com.oaktwister.views.roots.MainViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URL;

public class ViewHandler {

    private final Stage primaryStage;
    private final ViewModelFactory viewModelFactory;
    private final ControllerFactory controllerFactory;

    public ViewHandler(Stage primaryStage, ViewModelFactory viewModelFactory) {
        this.primaryStage = primaryStage;
        this.viewModelFactory = viewModelFactory;
        this.controllerFactory = new ControllerFactory(this, viewModelFactory);
    }

    public <T extends Parent> T loadRootView(@NotNull Class<?> resourceClass, String viewLocation) throws IOException {
        URL resourceUrl = resourceClass.getResource(viewLocation);
        FXMLLoader fxmlLoader = new FXMLLoader(resourceUrl);
        fxmlLoader.setControllerFactory(controllerFactory);
        return fxmlLoader.load();
    }

    public void loadCustomView(View view) throws IOException {
        URL resourceUrl = view.getClass().getResource(view.getViewLocation());
        FXMLLoader fxmlLoader = new FXMLLoader(resourceUrl);
        fxmlLoader.setRoot(view);
        fxmlLoader.setControllerFactory(aClass -> view);
        fxmlLoader.load();
    }

    public void showLandingView() throws IOException {
        Parent view = loadRootView(LandingViewController.class, Resources.Views.Roots.LANDING);
        Scene scene = new Scene(view);
        primaryStage.setTitle(Resources.Strings.App.TITLE);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showMainView() throws IOException {
        Parent view = loadRootView(MainViewController.class, Resources.Views.Roots.MAIN);
        Scene scene = new Scene(view);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public PlatformsPane getPlatformsPane() throws IOException {
        return new PlatformsPane(this, viewModelFactory.getPlatformsViewModel());
    }

    public PlatformPane getPlatformPane(PlatformViewModel viewModel) throws IOException {
        return new PlatformPane(this, viewModel);
    }

    public IdentitiesPane getIdentitiesPane() throws IOException {
        return new IdentitiesPane(this);
    }

    public AccountsPane getAccountsPane() throws IOException {
        return new AccountsPane(this, viewModelFactory.getAccountsViewModel());
    }

    public IdentityCell getIdentityCell() throws IOException {
        return new IdentityCell(this);
    }

    public AccountCell getAccountCell() throws IOException {
        return new AccountCell(this);
    }

    public ImageButtonBox getImageButtonBox() throws IOException {
        return new ImageButtonBox(this);
    }

}
