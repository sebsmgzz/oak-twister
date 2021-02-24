package views.lateralpane;

import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;

public class LateralPane extends Group {

    private final VBox vBox;
    private final Hyperlink identities;
    private final Hyperlink accounts;
    private final Hyperlink passwords;

    public LateralPane() {
        this.vBox = new VBox();
        this.identities = getNewButton("Identities");
        this.accounts = getNewButton("Accounts");
        this.passwords = getNewButton("Passwords");
        initialize();
    }

    public void initialize() {
        this.minWidth(100);
        this.getChildren().add(vBox);
        ObservableList<Node> children = this.vBox.getChildren();
        children.add(new Label("OakTwister"));
        children.add(new Separator());
        children.add(identities);
        children.add(accounts);
        children.add(passwords);
    }

    public Hyperlink getNewButton(String text) {
        Hyperlink hyperlink = new Hyperlink();
        hyperlink.setText(text);
        hyperlink.prefWidthProperty().bind(vBox.prefWidthProperty());
        return hyperlink;
    }

}
