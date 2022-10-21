package com.oaktwister.core;

import com.oaktwister.annotations.ViewDescriptor;
import com.oaktwister.services.resources.ImageResources;
import com.oaktwister.services.resources.ViewResources;
import com.oaktwister.services.resources.StringResources;
import com.oaktwister.viewmodels.models.AccountViewModel;
import com.oaktwister.viewmodels.models.IdentityViewModel;
import com.oaktwister.viewmodels.models.PlatformViewModel;
import com.oaktwister.views.accounts.AccountPane;
import com.oaktwister.views.identities.IdentityPane;
import com.oaktwister.views.laterals.ImageButtonBox;
import com.oaktwister.views.platforms.PlatformPane;
import com.oaktwister.views.accounts.AccountsPane;
import com.oaktwister.views.identities.IdentitiesPane;
import com.oaktwister.views.platforms.PlatformsPane;
import com.oaktwister.views.landings.LandingViewController;
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

    private URL getViewResourceUrl(@NotNull Class<?> viewClass) throws IOException {
        if (!viewClass.isAnnotationPresent(ViewDescriptor.class)) {
            // TODO: Create AnnotationNotFoundException class
            throw new IOException("View is missing the ViewDescriptor annotation. Cannot find view location.");
        }
        ViewDescriptor viewDescriptor = viewClass.getAnnotation(ViewDescriptor.class);
        String viewLocation = viewDescriptor.location();
        return viewClass.getResource(viewLocation);
    }

    public void loadCustomView(Object view) {
        try {
            Class<?> viewClass = view.getClass();
            URL viewResourceUrl = getViewResourceUrl(viewClass);
            FXMLLoader fxmlLoader = new FXMLLoader(viewResourceUrl);
            fxmlLoader.setRoot(view);
            fxmlLoader.setControllerFactory(aClass -> view);
            fxmlLoader.load();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    public <T extends Parent> T loadRootView(@NotNull Class<?> viewClass) {
        try {
            URL viewResourceUrl = getViewResourceUrl(viewClass);
            FXMLLoader fxmlLoader = new FXMLLoader(viewResourceUrl);
            fxmlLoader.setControllerFactory(controllerFactory);
            return fxmlLoader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    public void showLandingView() {
        Parent view = loadRootView(LandingViewController.class);
        Scene scene = new Scene(view);
        primaryStage.getIcons().add(new Image(ImageResources.Vikings.OAK));
        primaryStage.setTitle(StringResources.App.TITLE);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showMainView() {
        Parent view = loadRootView(MainViewController.class);
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
