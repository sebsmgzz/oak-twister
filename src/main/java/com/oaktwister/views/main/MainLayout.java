package com.oaktwister.views.main;

import com.oaktwister.annotations.ViewDescriptor;
import com.oaktwister.services.resources.ViewResources;
import com.oaktwister.utils.extensions.NodeUtil;
import com.oaktwister.utils.nodes.AnchorPaneUtil;
import com.oaktwister.views.widgets.ImageButtonBox;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

@ViewDescriptor(location = ViewResources.Main.LAYOUT)
public class MainLayout extends GridPane implements Initializable {

    @FXML private Label titleLabel;
    @FXML private ImageButtonBox accountsImageButtonBox;
    @FXML private ImageButtonBox platformsImageButtonBox;
    @FXML private ImageButtonBox identitiesImageButtonBox;
    @FXML private ImageButtonBox backButton;
    @FXML private ImageButtonBox settingsButton;
    @FXML private AnchorPane anchorPane;

    private final SimpleObjectProperty<MainPage> pageProperty;
    private final SimpleObjectProperty<Node> accountPageProperty;
    private final SimpleObjectProperty<Node> platformsPageProperty;
    private final SimpleObjectProperty<Node> identitiesPageProperty;

    public MainLayout() {
        pageProperty = new SimpleObjectProperty<>();
        accountPageProperty = new SimpleObjectProperty<>();
        platformsPageProperty = new SimpleObjectProperty<>();
        identitiesPageProperty = new SimpleObjectProperty<>();
        NodeUtil.loadControl(this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pageProperty.addListener(this::onPagePropertyChanged);
        accountsImageButtonBox.setOnAction(event -> pageProperty.set(MainPage.ACCOUNTS));
        platformsImageButtonBox.setOnAction(event -> pageProperty.set(MainPage.PLATFORMS));
        identitiesImageButtonBox.setOnAction(event -> pageProperty.set(MainPage.IDENTITIES));
    }

    public StringProperty titleProperty() {
        return titleLabel.textProperty();
    }

    public ObjectProperty<MainPage> pageProperty() {
        return pageProperty;
    }

    public ObjectProperty<Node> accountPageProperty() {
        return accountPageProperty;
    }

    public ObjectProperty<Node> platformsPageProperty() {
        return platformsPageProperty;
    }

    public ObjectProperty<Node> identitiesPageProperty() {
        return identitiesPageProperty;
    }

    public ObjectProperty<EventHandler<ActionEvent>> onBackActionProperty() {
        return backButton.onActionProperty();
    }

    public ObjectProperty<EventHandler<ActionEvent>> onSettingsActionProperty() {
        return settingsButton.onActionProperty();
    }

    private void onPagePropertyChanged(ObservableValue<? extends MainPage> observable,
                                       MainPage oldValue, MainPage newValue) {
        anchorPane.getChildren().clear();
        Node node = switch (newValue) {
            case ACCOUNTS -> accountPageProperty.get();
            case PLATFORMS -> platformsPageProperty.get();
            case IDENTITIES -> identitiesPageProperty.get();
        };
        AnchorPaneUtil.setEmptyAnchors(node);
        anchorPane.getChildren().add(node);
    }

}
