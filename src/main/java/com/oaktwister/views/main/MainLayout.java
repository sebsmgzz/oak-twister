package com.oaktwister.views.main;

import com.oaktwister.annotations.ViewDescriptor;
import com.oaktwister.services.resources.ViewResources;
import com.oaktwister.utils.extensions.NodeUtil;
import com.oaktwister.views.widgets.ImageButtonBox;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

@ViewDescriptor(location = ViewResources.Main.LAYOUT)
public class MainLayout extends BorderPane implements Initializable {

    @FXML private BorderPane borderPane;
    @FXML private ImageButtonBox accountsImageButtonBox;
    @FXML private ImageButtonBox platformsImageButtonBox;
    @FXML private ImageButtonBox identitiesImageButtonBox;
    @FXML private Button backButton;
    @FXML private Button settingsButton;

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
        switch (newValue) {
            case ACCOUNTS -> {
                Node centerNode = accountPageProperty.get();
                borderPane.setCenter(centerNode);
            }
            case PLATFORMS -> {
                Node centerNode = platformsPageProperty.get();
                borderPane.setCenter(centerNode);
            }
            case IDENTITIES -> {
                Node centerNode = identitiesPageProperty.get();
                borderPane.setCenter(centerNode);
            }
            default -> {
                borderPane.setCenter(null);
            }
        }
    }

}
