package views.components;

import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class Pane extends Group {

    VBox vBox;
    ImageView imageView;
    Hyperlink hyperlink;

    public Image getImage() {
        return imageView.getImage();
    }

    public void setImage(Image image) {
        imageView.setImage(image);
    }

    public String getText() {
        return hyperlink.getText();
    }

    public void setText(String text) {
        hyperlink.setText(text);
    }

    public Pane() {
        this.vBox = new VBox();
        this.imageView = new ImageView();
        this.hyperlink = new Hyperlink();
        initialize();
    }

    public void initialize() {
        this.getChildren().add(vBox);
        ObservableList<Node> children = vBox.getChildren();
        children.add(imageView);
        children.add(hyperlink);
    }

}
