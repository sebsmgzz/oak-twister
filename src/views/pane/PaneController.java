package views.pane;

import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import viewmodels.BaseViewModel;
import viewmodels.HomeViewModel;
import views.BaseController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public final class PaneController extends Group implements BaseController {

    @FXML
    private ImageView imageView;

    @FXML
    private Label label;

    public Image getRandomImage() {
        try {
            URL url = new URL("https://picsum.photos/200");
            BufferedImage capture = ImageIO.read(url);
            return SwingFXUtils.toFXImage(capture, null);
        } catch (IOException ignored) {
            return null;
        }
    }

}
