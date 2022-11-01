package com.oaktwister.core;

import com.oaktwister.services.resources.ImageResources;
import com.oaktwister.services.resources.StringResources;
import com.oaktwister.utils.extensions.NodeUtil;
import com.oaktwister.viewmodels.models.PlatformViewModel;
import com.oaktwister.controllers.dialogs.LoginFailedController;
import com.oaktwister.controllers.dialogs.EditPlatformDialogController;
import com.oaktwister.views.dialogs.EditPlatformDialogResult;
import com.oaktwister.controllers.layouts.LoginController;
import com.oaktwister.controllers.layouts.MainController;
import com.oaktwister.views.layouts.MainLayout;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public class Navigation {

    private final Stage primaryStage;
    private final UIContext ui;

    public Navigation(Stage primaryStage, UIContext ui) {
        this.primaryStage = primaryStage;
        this.ui = ui;
    }

    public void goToLogin() {
        LoginController controller = ui.controllers().login();
        Parent view = controller.getView();
        Scene scene = new Scene(view);
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image(ImageResources.Vikings.OAK));
        primaryStage.setTitle(StringResources.App.TITLE);
        primaryStage.show();
    }

    public void goToMain() {
        MainController controller = ui.controllers().main();
        MainLayout view = controller.getView();
        Scene scene = new Scene(view);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initDialog(Stage stage, Object dialogController) {
        Parent node = NodeUtil.loadWindow(dialogController);
        Scene scene = new Scene(node);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        Window window = primaryStage.getScene().getWindow();
        stage.initOwner(window);
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
