package views.home;

import views.pane.Pane;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import views.lateralpane.LateralPane;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.TilePane;
import viewmodels.HomeViewModel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class HomeController {

    @FXML
    private LateralPane lateralPane;

    @FXML
    private TilePane tilePane;

    private HomeViewModel viewModel;

    public void init(HomeViewModel viewModel) {
        this.viewModel = viewModel;
        for (Node node : tilePane.getChildren()) {
            Pane pane = (Pane) node;
            pane.setImage(getRandomImage());
        }
    }
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
