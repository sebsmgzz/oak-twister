package com.oaktwister.core;

import com.oaktwister.services.util.Resources;
import com.oaktwister.viewmodels.models.AccountViewModel;
import com.oaktwister.viewmodels.models.IdentityViewModel;
import com.oaktwister.viewmodels.models.PlatformViewModel;
import com.oaktwister.views.View;
import com.oaktwister.views.accounts.AccountPane;
import com.oaktwister.views.identities.IdentityPane;
import com.oaktwister.views.laterals.ImageButtonBox;
import com.oaktwister.views.platforms.PlatformPane;
import com.oaktwister.views.accounts.AccountsPane;
import com.oaktwister.views.identities.IdentitiesPane;
import com.oaktwister.views.platforms.PlatformsPane;
import com.oaktwister.views.landing.LandingViewController;
import com.oaktwister.views.main.MainViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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

    public <T extends Parent> T loadRootView(@NotNull Class<?> resourceClass, String viewLocation) {
        try {
            URL resourceUrl = resourceClass.getResource(viewLocation);
            FXMLLoader fxmlLoader = new FXMLLoader(resourceUrl);
            fxmlLoader.setControllerFactory(controllerFactory);
            return fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadCustomView(View view) {
        try {
            URL resourceUrl = view.getClass().getResource(view.getViewLocation());
            FXMLLoader fxmlLoader = new FXMLLoader(resourceUrl);
            fxmlLoader.setRoot(view);
            fxmlLoader.setControllerFactory(aClass -> view);
            fxmlLoader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    public void showLandingView() {
        Parent view = loadRootView(LandingViewController.class, Resources.Views.Roots.LANDING);
        Scene scene = new Scene(view);
        primaryStage.getIcons().add(new Image(Resources.Images.Vikings.OAK));
        primaryStage.setTitle(Resources.Strings.App.TITLE);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showMainView() {
        Parent view = loadRootView(MainViewController.class, Resources.Views.Roots.MAIN);
        Scene scene = new Scene(view);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public PlatformsPane getPlatformsPane() {
        return new PlatformsPane(this, viewModelFactory.getPlatformsViewModel());
    }

    public PlatformPane getPlatformPane(PlatformViewModel viewModel) {
        return new PlatformPane(this, viewModel);
    }

    public IdentitiesPane getIdentitiesPane() {
        return new IdentitiesPane(this, viewModelFactory.getIdentitiesViewModel());
    }

    public AccountsPane getAccountsPane() {
        return new AccountsPane(this, viewModelFactory.getAccountsViewModel());
    }

    public IdentityPane getIdentityPane(IdentityViewModel viewModel) {
        return new IdentityPane(this, viewModel);
    }

    public AccountPane getAccountBox(AccountViewModel viewModel) {
        return new AccountPane(this, viewModel);
    }

    public ImageButtonBox getImageButtonBox() {
        return new ImageButtonBox(this);
    }

}
