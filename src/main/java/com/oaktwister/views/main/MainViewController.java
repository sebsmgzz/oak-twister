package com.oaktwister.views.main;

import com.oaktwister.annotations.ViewDescriptor;
import com.oaktwister.core.ViewMediator;
import com.oaktwister.services.resources.ImageResources;
import com.oaktwister.services.resources.StringResources;
import com.oaktwister.services.resources.ViewResources;
import com.oaktwister.viewmodels.roots.MainViewModel;
import com.oaktwister.views.laterals.ImageButtonBox;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@ViewDescriptor(location = ViewResources.Main.MAIN_VIEW)
public class MainViewController implements Initializable {

    private final ViewMediator viewMediator;
    private final MainViewModel viewModel;

    private final Property<Node> centerNodeProperty;

    private final ImageButtonBox accountsButton;
    private final ImageButtonBox platformsButton;
    private final ImageButtonBox identitiesButton;

    @FXML private BorderPane borderPane;
    @FXML private VBox vbox;
    @FXML private Button backButton;
    @FXML private Button settingsButton;

    public MainViewController(ViewMediator viewMediator, MainViewModel viewModel) {
        this.viewMediator = viewMediator;
        this.viewModel = viewModel;
        centerNodeProperty = new SimpleObjectProperty<Node>();
        accountsButton = viewMediator.controlFactory.getImageButtonBox();
        platformsButton = viewMediator.controlFactory.getImageButtonBox();
        identitiesButton = viewMediator.controlFactory.getImageButtonBox();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Bind the borderPane's center node with the centerNodeProperty
        borderPane.centerProperty().bind(centerNodeProperty);

        // Add and initialize lateral buttons
        ObservableList<Node> vboxChildren = vbox.getChildren();

        // Accounts button
        vboxChildren.add(accountsButton);
        accountsButton.setText(StringResources.ACCOUNTS_BUTTON_TEXT);
        accountsButton.setImage(ImageResources.FontAwesome.USERS_SOLID);
        accountsButton.setOnAction(event -> viewModel.sectionProperty().set(Section.ACCOUNTS));

        // Platforms button
        vboxChildren.add(platformsButton);
        platformsButton.setText(StringResources.PLATFORMS_BUTTON_TEXT);
        platformsButton.setImage(ImageResources.FontAwesome.LAYER_GROUP_SOLID);
        platformsButton.setOnAction(event -> viewModel.sectionProperty().set(Section.PLATFORMS));

        // Identities button
        vboxChildren.add(identitiesButton);
        identitiesButton.setText(StringResources.IDENTITIES_BUTTON_TEXT);
        identitiesButton.setImage(ImageResources.FontAwesome.FINGERPRINT_SOLID);
        identitiesButton.setOnAction(event -> viewModel.sectionProperty().set(Section.IDENTITIES));

        // Set the button's actions
        backButton.setOnAction(event -> viewMediator.showLandingView());
        settingsButton.setOnAction(event -> { /* TODO: MainViewController::onSettingsButtonClick */ });

        // Bind the viewModel with the centerNodeProperty
        viewModel.sectionProperty().addListener((observable, oldValue, newValue) -> {
            Node node = switch (newValue) {
                case ACCOUNTS -> viewMediator.controlFactory.getAccountsPane();
                case PLATFORMS -> viewMediator.controlFactory.getPlatformsPane();
                case IDENTITIES -> viewMediator.controlFactory.getIdentitiesPane();
            };
            centerNodeProperty.setValue(node);
        });

        // Initialize viewModel in AccountsSection
        viewModel.sectionProperty().set(Section.ACCOUNTS);

    }

}
