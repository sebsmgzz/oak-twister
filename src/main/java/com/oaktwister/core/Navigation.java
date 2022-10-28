package com.oaktwister.core;

import com.oaktwister.services.resources.ImageResources;
import com.oaktwister.services.resources.StringResources;
import com.oaktwister.utils.extensions.NodeUtil;
import com.oaktwister.viewmodels.models.PlatformViewModel;
import com.oaktwister.viewmodels.roots.LandingViewModel;
import com.oaktwister.viewmodels.roots.MainViewModel;
import com.oaktwister.views.dialogs.platforms.EditPlatformDialogController;
import com.oaktwister.views.dialogs.platforms.EditPlatformDialogResult;
import com.oaktwister.views.windows.landings.LandingViewController;
import com.oaktwister.views.windows.main.MainViewController;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public class Navigation {

    private final Stage primaryStage;
    public final ViewModelFactory viewModelFactory;

    public Navigation(Stage primaryStage, ViewModelFactory viewModelFactory) {
        this.primaryStage = primaryStage;
        this.viewModelFactory = viewModelFactory;
    }

    private void showDialog(Stage stage, Object dialogController) {
        Parent node = NodeUtil.loadWindow(dialogController);
        Scene scene = new Scene(node);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        Window window = primaryStage.getScene().getWindow();
        stage.initOwner(window);
        stage.showAndWait();
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

    public EditPlatformDialogResult showEditPlatformDialog(PlatformViewModel platformViewModel) {
        Stage stage = new Stage();
        EditPlatformDialogController controller = new EditPlatformDialogController(stage, platformViewModel);
        showDialog(stage, controller);
        return controller.resultProperty().get();
    }

}
