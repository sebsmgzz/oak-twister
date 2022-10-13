package com.oaktwister.views.main;

import com.oaktwister.services.Resources;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;

public class PageControl extends VBox {

    @FXML
    private Label label;
    @FXML
    public FlowPane flowPane;

    public String getTitle() {
        return label.getText();
    }

    public void setTitle(String value) {
        label.setText(value);
    }

    public ObservableList<Node> getContent() {
        return flowPane.getChildren();
    }

    public PageControl() throws IOException {
        super();
        URL resourceUrl = PageControl.class.getResource(Resources.Views.Main.PAGE);
        FXMLLoader fxmlLoader = new FXMLLoader(resourceUrl);
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        fxmlLoader.load();
    }

    public StringProperty titleProperty() {
        return label.textProperty();
    }

}
