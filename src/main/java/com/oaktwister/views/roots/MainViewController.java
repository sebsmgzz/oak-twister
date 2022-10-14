package com.oaktwister.views.roots;

import com.oaktwister.core.ViewFactory;
import com.oaktwister.services.Resources;
import com.oaktwister.viewmodels.main.MainViewModel;
import com.oaktwister.views.controls.ImageButtonBox;
import com.oaktwister.views.layouts.AccountsPane;
import com.oaktwister.views.layouts.IdentitiesPane;
import com.oaktwister.views.layouts.PlatformsPane;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

    private final ViewFactory viewFactory;
    private final MainViewModel viewModel;

    @FXML private BorderPane borderPane;
    @FXML private ImageButtonBox identitiesButton;
    @FXML private ImageButtonBox platformsButton;
    @FXML private ImageButtonBox accountsButton;

    private final Property<Node> centerNodeProperty;

    public MainViewController(ViewFactory viewFactory, MainViewModel viewModel) {
        this.viewFactory = viewFactory;
        this.viewModel = viewModel;
        centerNodeProperty = new SimpleObjectProperty<Node>(getSection());
    }

    private Node getSection() {
        try {
            Node node = null;
            switch (viewModel.getSection()) {
                case ACCOUNTS -> {
                    AccountsPane accountsPane = new AccountsPane();
                    accountsPane.setTitle("Accounts");
                    // TODO: Bind accountsPane to viewModel.accounts()
                    node = accountsPane;
                }
                case PLATFORMS -> {
                    PlatformsPane platformsPane = new PlatformsPane();
                    platformsPane.setTitle("Platforms");
                    // TODO: Bind platformsPane to viewModel.platforms()
                    node = platformsPane;
                }
                case IDENTITIES -> {
                    IdentitiesPane identitiesPane = new IdentitiesPane();
                    identitiesPane.setTitle("Identities");
                    // TODO: Bind identitiesPane to viewModel.identities()
                    node = identitiesPane;
                }
            }
            return node;
        } catch (IOException ex) {
            ex.printStackTrace();
            // TODO: Raise alert?
            return null;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // The centerNodeProperty listens for changes from the view model property and
        // converts the type from Section to Node. The borderPane gets updated due to its
        // binding with the centerNodeProperty. The only downside is that bidirectional binding
        // won't reach the view model, but that is fine for this case (since do not require it).
        viewModel.sectionProperty().addListener(s -> centerNodeProperty.setValue(getSection()));
        borderPane.centerProperty().bind(centerNodeProperty);

        // Bind button's actions to change the view model section accordingly
        identitiesButton.setOnAction(e -> viewModel.setSection(Section.IDENTITIES));
        platformsButton.setOnAction(e -> viewModel.setSection(Section.PLATFORMS));
        accountsButton.setOnAction(e -> viewModel.setSection(Section.ACCOUNTS));

        // Set button's images
        identitiesButton.setImage(Resources.Images.SocialNetwork.FRIENDS);
        platformsButton.setImage(Resources.Images.Cyberpunk.PLATFORM);
        accountsButton.setImage(Resources.Images.HelpSupport.KEY);

    }

    @FXML
    public void onBackButtonAction(ActionEvent actionEvent) throws IOException {
        viewFactory.showLandingView();
    }

}
