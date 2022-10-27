package com.oaktwister.core;

import com.oaktwister.services.resources.ImageResources;
import com.oaktwister.services.resources.StringResources;
import com.oaktwister.utils.extensions.NodeUtil;
import com.oaktwister.viewmodels.models.PlatformViewModel;
import com.oaktwister.viewmodels.roots.LandingViewModel;
import com.oaktwister.viewmodels.roots.MainViewModel;
import com.oaktwister.views.dialogs.platforms.EditPlatformDialogPane;
import com.oaktwister.views.windows.landings.LandingViewController;
import com.oaktwister.views.windows.main.MainViewController;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Optional;

public class Navigation {

    private final Stage primaryStage;
    public final ViewModelFactory viewModelFactory;

    public Navigation(Stage primaryStage, ViewModelFactory viewModelFactory) {
        this.primaryStage = primaryStage;
        this.viewModelFactory = viewModelFactory;
    }

    public void goToLandingView() {
        LandingViewModel viewModel = viewModelFactory.getLandingViewModel();
        LandingViewController controller = new LandingViewController(this, viewModel);
        Parent node = NodeUtil.loadWindow(controller);
        Scene scene = new Scene(node);
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image(ImageResources.Vikings.OAK));
        primaryStage.setTitle(StringResources.App.TITLE);
        primaryStage.show();
    }

    public void goToMainView() {
        MainViewModel viewModel = viewModelFactory.getMainViewModel();
        MainViewController controller = new MainViewController(this, viewModel);
        Parent node = NodeUtil.loadWindow(controller);
        Scene scene = new Scene(node);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public Optional<ButtonType> showEditPlatformDialog(PlatformViewModel platformViewModel) {
        EditPlatformDialogPane dialogPane = new EditPlatformDialogPane();
        dialogPane.setViewModel(platformViewModel);
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(dialogPane);
        return dialog.showAndWait();
    }

}
