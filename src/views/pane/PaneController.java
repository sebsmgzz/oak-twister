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

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class PaneController extends Group {

    @FXML
    private ImageView imageView;

    @FXML
    private Label labelView;

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
