package com.oaktwister.views.main;

import com.oaktwister.annotations.ViewDescriptor;
import com.oaktwister.core.ViewHandler;
import com.oaktwister.services.resources.ImageResources;
import com.oaktwister.services.resources.StringResources;
import com.oaktwister.services.resources.ViewResources;
import com.oaktwister.viewmodels.roots.MainViewModel;
import com.oaktwister.views.laterals.ImageButtonBox;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@ViewDescriptor(location = ViewResources.Main.MAIN_VIEW)
public class MainViewController implements Initializable {

    private final ViewHandler viewHandler;
    private final MainViewModel viewModel;

    @FXML private BorderPane borderPane;
    @FXML private VBox vbox;

    private final ImageButtonBox accountsButton;
    private final ImageButtonBox platformsButton;
    private final ImageButtonBox identitiesButton;

    private final Property<Node> centerNodeProperty;

    public MainViewController(ViewHandler viewHandler, MainViewModel viewModel) throws IOException {
        this.viewHandler = viewHandler;
        this.viewModel = viewModel;
        accountsButton = viewHandler.getImageButtonBox();
        platformsButton = viewHandler.getImageButtonBox();
        identitiesButton = viewHandler.getImageButtonBox();
        centerNodeProperty = new SimpleObjectProperty<Node>(getSection());
    }

    private Node getSection() {
        return switch (viewModel.getSection()) {
            case ACCOUNTS -> viewHandler.getAccountsPane();
            case PLATFORMS -> viewHandler.getPlatformsPane();
            case IDENTITIES -> viewHandler.getIdentitiesPane();
        };
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // The centerNodeProperty listens for changes from the view model property and
        // converts the type from Section to Node. The borderPane gets updated due to its
        // binding with the centerNodeProperty. The only downside is that bidirectional binding
        // won't reach the view model, but that is fine for this case (since do not require it).
        viewModel.sectionProperty().addListener(s -> centerNodeProperty.setValue(getSection()));
        borderPane.centerProperty().bind(centerNodeProperty);

        // Mount buttons
        vbox.getChildren().add(accountsButton);
        vbox.getChildren().add(platformsButton);
        vbox.getChildren().add(identitiesButton);
        identitiesButton.setMaxWidth(Double.MAX_VALUE);

        // Bind button's actions to change the view model section accordingly
        accountsButton.setOnAction(e -> viewModel.setSection(Section.ACCOUNTS));
        platformsButton.setOnAction(e -> viewModel.setSection(Section.PLATFORMS));
        identitiesButton.setOnAction(e -> viewModel.setSection(Section.IDENTITIES));

        // Set button's texts
        accountsButton.setText(StringResources.ACCOUNTS_BUTTON_TEXT);
        platformsButton.setText(StringResources.PLATFORMS_BUTTON_TEXT);
        identitiesButton.setText(StringResources.IDENTITIES_BUTTON_TEXT);

        // Set button's images
        identitiesButton.setImage(ImageResources.FontAwesome.FINGERPRINT_SOLID);
        platformsButton.setImage(ImageResources.FontAwesome.LAYER_GROUP_SOLID);
        accountsButton.setImage(ImageResources.FontAwesome.USERS_SOLID);

    }

    @FXML
    public void onBackButtonAction(ActionEvent actionEvent) throws IOException {
        viewHandler.showLandingView();
    }

}
