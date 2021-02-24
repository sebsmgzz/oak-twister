package views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.net.URL;

public abstract class BaseView {

    public abstract Parent getNode();

    public abstract Controller getController();

    protected FXMLLoader getLoader(String resourcePath) {
        FXMLLoader loader = new FXMLLoader();
        URL url = getClass().getResource(resourcePath);
        loader.setLocation(url);
        return loader;
    }

}
