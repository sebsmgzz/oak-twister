package com.oaktwister.app.utils.extensions;

import com.oaktwister.app.annotations.ViewDescriptor;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URL;

public class FXMLUtil {

    public static URL getViewResourceUrl(@NotNull Class<?> viewClass) throws IOException {
        if (!viewClass.isAnnotationPresent(ViewDescriptor.class)) {
            // TODO: Create AnnotationNotFoundException class
            throw new IOException(String.format(
                    "View of type %s is missing the %s annotation.",
                    viewClass.getTypeName(),
                    ViewDescriptor.class.getTypeName()));
        }
        ViewDescriptor viewDescriptor = viewClass.getAnnotation(ViewDescriptor.class);
        String viewLocation = viewDescriptor.location();
        return viewClass.getResource(viewLocation);
    }

    public static <T extends Node> T loadControl(@NotNull T node) {
        try {
            Class<? extends Node> nodeClass = node.getClass();
            URL resourceUrl = getViewResourceUrl(nodeClass);
            return loadControl(resourceUrl, node);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    public static <T extends Node> T loadControl(@NotNull Class<T> nodeClass, @NotNull T node) {
        try {
            URL resourceUrl = getViewResourceUrl(nodeClass);
            return loadControl(resourceUrl, node);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    public static <T extends Node> T loadControl(@NotNull URL resourceUrl, @NotNull Node node) {
        try {
            FXMLLoader loader = new FXMLLoader(resourceUrl);
            loader.setLocation(resourceUrl);
            loader.setRoot(node);
            loader.setControllerFactory(aClass -> node);
            return loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    public static Stage getDialogStage(Stage primaryStage) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        Window window = primaryStage.getScene().getWindow();
        stage.initOwner(window);
        return stage;
    }

}
