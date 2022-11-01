package com.oaktwister.utils.extensions;

import com.oaktwister.annotations.ViewDescriptor;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URL;

public class NodeUtil {

    public static URL getViewResourceUrl(@NotNull Class<?> viewClass) throws IOException {
        if (!viewClass.isAnnotationPresent(ViewDescriptor.class)) {
            // TODO: Create AnnotationNotFoundException class
            throw new IOException("View is missing the ViewDescriptor annotation. Cannot find view location.");
        }
        ViewDescriptor viewDescriptor = viewClass.getAnnotation(ViewDescriptor.class);
        String viewLocation = viewDescriptor.location();
        return viewClass.getResource(viewLocation);
    }

    public static <T extends Node> T loadControl(@NotNull Node node) {
        try {
            Class<?> nodeClass = node.getClass();
            URL resourceUrl = getViewResourceUrl(nodeClass);
            FXMLLoader loader = new FXMLLoader(resourceUrl);
            loader.setRoot(node);
            loader.setControllerFactory(aClass -> node);
            return loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    public static <T extends Parent> T loadWindow(@NotNull Object window) {
        try {
            Class<?> nodeClass = window.getClass();
            URL resourceUrl = getViewResourceUrl(nodeClass);
            FXMLLoader loader = new FXMLLoader(resourceUrl);
            loader.setControllerFactory(aClass -> window);
            return loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

}
