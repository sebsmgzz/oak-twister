package com.oaktwister.views.controls;

import com.oaktwister.core.ViewHandler;
import com.oaktwister.services.util.Resources;
import com.oaktwister.viewmodels.models.PlatformViewModel;
import com.oaktwister.views.View;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import java.net.URL;
import java.util.ResourceBundle;

public class PlatformPane extends StackPane implements View {

    private static final String DATE_TIME_PREFIX = "Created at: "; // TODO: Put this in a label in the xml

    private final ViewHandler viewHandler;
    private final PlatformViewModel viewModel;

    @FXML private Button mainButton;
    @FXML private Label idLabel;
    @FXML private ImageView imageView;
    @FXML private Label nameLabel;
    @FXML private Label createdAtLabel;
    @FXML private Button deleteButton;

    public PlatformPane(ViewHandler viewHandler, PlatformViewModel viewModel) {
        super();
        this.viewHandler = viewHandler;
        this.viewModel = viewModel;
        viewHandler.loadCustomView(this);
    }

    @Override
    public String getViewLocation() {
        return Resources.Views.Controls.PLATFORM_PANE;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Styling
        this.setOnMouseEntered(event -> deleteButton.setVisible(true));
        this.setOnMouseExited(event -> deleteButton.setVisible(false));
        StackPane.setAlignment(deleteButton, Pos.TOP_RIGHT);

        // Property binding
        viewModel.idProperty().addListener((observable, oldValue, newValue) ->
                idLabel.setText(newValue.toString()));
        viewModel.imageProperty().addListener((observable, oldValue, newValue) ->
                imageView.setImage(newValue));
        nameLabel.textProperty().bindBidirectional(viewModel.nameProperty());
        viewModel.createdAtProperty().addListener((observable, oldValue, newValue) ->
                createdAtLabel.setText(newValue.toString())); // TODO: Use datetime formatter

    }

    public PlatformViewModel getViewModel() {
        return viewModel;
    }

}
