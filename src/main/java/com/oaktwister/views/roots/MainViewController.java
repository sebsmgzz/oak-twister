package com.oaktwister.views.roots;

import com.oaktwister.core.ViewHandler;
import com.oaktwister.services.Resources;
import com.oaktwister.viewmodels.main.MainViewModel;
import com.oaktwister.views.controls.ImageButtonBox;
import com.oaktwister.views.util.Section;
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

public class MainViewController implements Initializable {

    private final ViewHandler viewHandler;
    private final MainViewModel viewModel;

    @FXML private BorderPane borderPane;
    @FXML private VBox vBox;

    private final ImageButtonBox identitiesButton;
    private final ImageButtonBox platformsButton;
    private final ImageButtonBox accountsButton;

    private final Property<Node> centerNodeProperty;

    public MainViewController(ViewHandler viewHandler, MainViewModel viewModel) throws IOException {
        this.viewHandler = viewHandler;
        this.viewModel = viewModel;
        identitiesButton = viewHandler.getImageButtonBox();
        platformsButton = viewHandler.getImageButtonBox();
        accountsButton = viewHandler.getImageButtonBox();
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
        vBox.getChildren().add(identitiesButton);
        vBox.getChildren().add(platformsButton);
        vBox.getChildren().add(accountsButton);

        // Bind button's actions to change the view model section accordingly
        identitiesButton.setOnAction(e -> viewModel.setSection(Section.IDENTITIES));
        platformsButton.setOnAction(e -> viewModel.setSection(Section.PLATFORMS));
        accountsButton.setOnAction(e -> viewModel.setSection(Section.ACCOUNTS));

        // Set button's texts
        identitiesButton.setText(Resources.Strings.IDENTITIES_BUTTON_TEXT);
        platformsButton.setText(Resources.Strings.PLATFORMS_BUTTON_TEXT);
        accountsButton.setText(Resources.Strings.ACCOUNTS_BUTTON_TEXT);

        // Set button's images
        identitiesButton.setImage(Resources.Images.SocialNetwork.FRIENDS);
        platformsButton.setImage(Resources.Images.Cyberpunk.PLATFORM);
        accountsButton.setImage(Resources.Images.HelpSupport.KEY);

    }

    @FXML
    public void onBackButtonAction(ActionEvent actionEvent) throws IOException {
        viewHandler.showLandingView();
    }

}
