package com.oaktwister.views.main;

import com.oaktwister.core.ViewFactory;
import com.oaktwister.services.Resources;
import com.oaktwister.viewmodels.main.MainViewModel;
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

    @FXML
    private BorderPane borderPane;
    @FXML
    private LateralButtonControl identitiesButtonControl;
    @FXML
    private LateralButtonControl platformsButtonControl;
    @FXML
    private LateralButtonControl accountsButtonControl;

    private final Property<Node> centerNodeProperty;

    public MainViewController(ViewFactory viewFactory, MainViewModel viewModel) {
        this.viewFactory = viewFactory;
        this.viewModel = viewModel;
        centerNodeProperty = new SimpleObjectProperty<Node>(getSection());
    }

    private Node getSection() {
        try {
            return switch (viewModel.getSection()) {
                case ACCOUNTS -> viewFactory.getView(
                    AccountsViewController.class, Resources.Views.Main.ACCOUNTS);
                case PLATFORMS -> viewFactory.getView(
                    PlatformsViewController.class, Resources.Views.Main.PLATFORMS);
                case IDENTITIES -> viewFactory.getView(
                    IdentitiesViewController.class, Resources.Views.Main.IDENTITIES);
            };
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
        // binding with the centerNodeProperty. Only downside is that bidirectional binding
        // won't reach the view model, but that is fine for this case (since do not require it).
        viewModel.sectionProperty().addListener(s -> centerNodeProperty.setValue(getSection()));
        borderPane.centerProperty().bind(centerNodeProperty);

        // Bind button's actions to change the view model section accordingly
        identitiesButtonControl.setOnAction(e -> viewModel.setSection(Section.IDENTITIES));
        platformsButtonControl.setOnAction(e -> viewModel.setSection(Section.PLATFORMS));
        accountsButtonControl.setOnAction(e -> viewModel.setSection(Section.ACCOUNTS));

        // Set button's images
        identitiesButtonControl.setImage(Resources.Images.SocialNetwork.FRIENDS);
        platformsButtonControl.setImage(Resources.Images.Cyberpunk.PLATFORM);
        accountsButtonControl.setImage(Resources.Images.HelpSupport.KEY);

    }

    @FXML
    public void onBackButtonAction(ActionEvent actionEvent) throws IOException {
        viewFactory.showLandingView();
    }

}
