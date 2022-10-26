package com.oaktwister.core;

import com.oaktwister.annotations.ViewDescriptor;
import com.oaktwister.services.resources.ImageResources;
import com.oaktwister.services.resources.StringResources;
import com.oaktwister.viewmodels.roots.LandingViewModel;
import com.oaktwister.viewmodels.roots.MainViewModel;
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

public class ViewMediator {

    private final Stage primaryStage;
    private final ViewModelFactory viewModelFactory;

    public final ControlFactory controlFactory;
    public final DialogFactory dialogFactory;

    public ViewMediator(Stage primaryStage, ViewModelFactory viewModelFactory) {
        this.primaryStage = primaryStage;
        this.viewModelFactory = viewModelFactory;
        controlFactory = new ControlFactory(this, viewModelFactory);
        dialogFactory = new DialogFactory(this, viewModelFactory);
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

    public void loadViewControl(Object viewControl) {
        try {
            Class<?> viewClass = viewControl.getClass();
            URL viewResourceUrl = getViewResourceUrl(viewClass);
            FXMLLoader fxmlLoader = new FXMLLoader(viewResourceUrl);
            fxmlLoader.setRoot(viewControl);
            fxmlLoader.setControllerFactory(aClass -> viewControl);
            fxmlLoader.load();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    public <T extends Parent> T loadRootView(@NotNull Object viewController) {
        try {
            Class<?> viewClass = viewController.getClass();
            URL viewResourceUrl = getViewResourceUrl(viewClass);
            FXMLLoader fxmlLoader = new FXMLLoader(viewResourceUrl);
            fxmlLoader.setControllerFactory(aClass -> viewController);
            return fxmlLoader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    public void showLandingView() {
        LandingViewModel viewModel = viewModelFactory.getLandingViewModel();
        LandingViewController controller = new LandingViewController(this, viewModel);
        Parent view = loadRootView(controller);
        Scene scene = new Scene(view);
        primaryStage.getIcons().add(new Image(ImageResources.Vikings.OAK));
        primaryStage.setTitle(StringResources.App.TITLE);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showMainView() {
        MainViewModel viewModel = viewModelFactory.getMainViewModel();
        MainViewController controller = new MainViewController(this, viewModel);
        Parent view = loadRootView(controller);
        Scene scene = new Scene(view);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
