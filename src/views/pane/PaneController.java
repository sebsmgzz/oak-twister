package views.pane;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import viewmodels.PaneViewModel;
import views.Controller;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public final class PaneController extends Controller {

    public static final String SOURCE =  "/views/pane/PaneView.fxml";
    private Parent node;
    private PaneViewModel viewModel;

    @FXML
    private ImageView imageView;

    @FXML
    private Label label;

    @Override
    public Parent getNode() {
        return node;
    }

    public void setup(Parent node, PaneViewModel viewModel) {
        this.node = node;
        this.viewModel = viewModel;
        this.imageView.setImage(getRandomImage());
        this.label.setText("Placeholder");
    }

    public WritableImage getRandomImage() {
        try {
            URL url = new URL("https://picsum.photos/200");
            BufferedImage capture = ImageIO.read(url);
            return SwingFXUtils.toFXImage(capture, null);
        } catch (IOException ignored) {
            return null;
        }
    }

}
