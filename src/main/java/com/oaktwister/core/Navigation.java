package com.oaktwister.core;

import com.oaktwister.services.resources.ImageResources;
import com.oaktwister.services.resources.StringResources;
import com.oaktwister.utils.extensions.NodeUtil;
import com.oaktwister.viewmodels.models.PlatformViewModel;
import com.oaktwister.viewmodels.roots.LoginViewModel;
import com.oaktwister.viewmodels.roots.MainViewModel;
import com.oaktwister.views.dialogs.logins.LoginFailedController;
import com.oaktwister.views.dialogs.platforms.EditPlatformDialogController;
import com.oaktwister.views.dialogs.platforms.EditPlatformDialogResult;
import com.oaktwister.views.windows.login.LoginController;
import com.oaktwister.views.windows.main.MainController;

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

    private void initDialog(Stage stage, Object dialogController) {
        Parent node = NodeUtil.loadWindow(dialogController);
        Scene scene = new Scene(node);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        Window window = primaryStage.getScene().getWindow();
        stage.initOwner(window);
    }

    public void goToLogin() {
        LoginViewModel viewModel = viewModelFactory.getLoginViewModel();
        LoginController controller = new LoginController(this, viewModel);
        Parent node = NodeUtil.loadWindow(controller);
        Scene scene = new Scene(node);
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image(ImageResources.Vikings.OAK));
        primaryStage.setTitle(StringResources.App.TITLE);
        primaryStage.show();
    }

    public void goToMain() {
        MainViewModel viewModel = viewModelFactory.getMainViewModel();
        MainController controller = new MainController(this, viewModel);
        Parent node = NodeUtil.loadWindow(controller);
        Scene scene = new Scene(node);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public EditPlatformDialogResult showEditPlatformDialog(PlatformViewModel platformViewModel) {
        Stage stage = new Stage();
        EditPlatformDialogController controller = new EditPlatformDialogController(stage, platformViewModel);
        initDialog(stage, controller);
        stage.showAndWait();
        return controller.resultProperty().get();
    }

    public void showLoginFailedAlert(String message) {
        Stage stage = new Stage();
        LoginFailedController controller = new LoginFailedController(stage);
        initDialog(stage, controller);
        controller.messageProperty().set(message);
        stage.showAndWait();
    }

}
