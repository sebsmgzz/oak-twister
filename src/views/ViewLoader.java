package views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public final class ViewLoader {

    private final FXMLLoader loader;

    public ViewLoader(ControllerFactory controllerFactory) {
        this.loader = new FXMLLoader();
        this.loader.setControllerFactory(controllerFactory);
    }

    public Parent load(String viewSource) throws IOException {
        loader.setLocation(getClass().getResource(viewSource));
        return loader.load();
    }

}
