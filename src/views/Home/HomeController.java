package views.Home;

import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import viewmodels.HomeViewModel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class HomeController {

    @FXML
    private VBox vBox;
    @FXML
    private TilePane tilePane;

    private HomeViewModel viewModel;

    public void init(HomeViewModel viewModel) {
        this.viewModel = viewModel;
        ObservableList<Node> children = this.tilePane.getChildren();
        for(int i = 0; i < 5; i++) {
            children.add(getRandomItem(getRandomImage(), String.valueOf(i)));
        }
    }

    public VBox getRandomItem(Image image, String label) {
        VBox box = new VBox();
        ObservableList<Node> children = box.getChildren();
        children.add(new ImageView(image));
        children.add(new Label(label));
        return box;
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
