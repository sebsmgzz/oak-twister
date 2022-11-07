package com.oaktwister.app.utils.extensions;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public class AnchorPaneUtil {

    public static void setAnchors(Node node, Double value) {
        AnchorPane.setTopAnchor(node, 0.0);
        AnchorPane.setRightAnchor(node, 0.0);
        AnchorPane.setBottomAnchor(node, 0.0);
        AnchorPane.setLeftAnchor(node, 0.0);
    }

    public static void setEmptyAnchors(Node node) {
        setAnchors(node, 0.0);
    }

}
