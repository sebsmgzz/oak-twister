package com.oaktwister.app.views.widgets.dialogs;

import com.oaktwister.app.annotations.ViewDescriptor;
import com.oaktwister.app.services.resources.ViewResources;
import com.oaktwister.app.utils.extensions.AnchorPaneUtil;
import com.oaktwister.app.utils.extensions.FXMLUtil;
import com.oaktwister.app.utils.extensions.PropertyUtil;
import com.oaktwister.app.utils.listeners.ListItemAddedListener;
import com.oaktwister.app.utils.listeners.ListItemRemovedListener;
import com.oaktwister.app.utils.listeners.SetItemAddedListener;
import com.oaktwister.app.utils.listeners.SetItemRemovedListener;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

@ViewDescriptor(location = ViewResources.Widgets.Dialogs.DIALOG_FRAME)
public class DialogFrame extends AnchorPane implements Initializable {

    // UI
    @FXML private ImageView iconImageView;
    @FXML private Label titleLabel;
    @FXML private AnchorPane contentAnchorPane;
    @FXML private HBox buttonsHBox;

    // Properties
    private final SimpleObjectProperty<Node> contentProperty;
    private final SimpleObjectProperty<DialogResult> resultProperty;
    private final SimpleListProperty<DialogButton> buttonsProperty;

    // Listeners
    private final ListItemAddedListener<DialogButton> onButtonAddedListener;
    private final ListItemRemovedListener<DialogButton> onButtonRemovedListener;

    public DialogFrame() {
        contentProperty = new SimpleObjectProperty<>();
        resultProperty = new SimpleObjectProperty<>();
        buttonsProperty = new SimpleListProperty<>(FXCollections.observableArrayList());
        onButtonAddedListener = new ListItemAddedListener<>(this::onButtonAdded);
        onButtonRemovedListener = new ListItemRemovedListener<>(this::onButtonRemoved);
        FXMLUtil.loadControl(this);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttonsProperty.addListener(onButtonAddedListener);
        buttonsProperty.addListener(onButtonRemovedListener);
        contentProperty.addListener((observableValue, oldValue, newValue) -> {
            ObservableList<Node> children = contentAnchorPane.getChildren();
            children.clear();
            AnchorPaneUtil.setEmptyAnchors(newValue);
            children.add(newValue);
        });
    }

    private void onButtonAdded(DialogButton dialogButton) {
        dialogButton.setOnAction(event -> {
            DialogButton button = event.getSender();
            resultProperty.set(button.getResult());
        });
        buttonsHBox.getChildren().add(dialogButton);
    }

    private void onButtonRemoved(DialogButton dialogButton) {
        buttonsHBox.getChildren().remove(dialogButton);
    }

    public ObjectProperty<Image> iconProperty() {
        return iconImageView.imageProperty();
    }
    public Image getIcon() {
        return iconProperty().get();
    }
    public void setIcon(Image value) {
        iconProperty().set(value);
    }

    public StringProperty titleProperty() {
        return titleLabel.textProperty();
    }
    public String getTitle() {
        return titleProperty().get();
    }
    public void setTitle(String value) {
        titleProperty().set(value);
    }

    public ObjectProperty<Node> contentProperty() {
        return contentProperty;
    }
    public Node getContent() {
        return contentProperty().get();
    }
    public void setContent(Node value) {
        contentProperty().set(value);
    }

    public ReadOnlyObjectProperty<DialogResult> resultProperty() {
        return resultProperty;
    }
    public DialogResult getResult() {
        return resultProperty().get();
    }

    public ListProperty<DialogButton> buttonsProperty() {
        return buttonsProperty;
    }
    public ObservableList<DialogButton> getButtons() {
        return buttonsProperty().get();
    }
    public boolean addButton(DialogButton button) {
        return buttonsProperty().add(button);
    }
    public boolean addButtons(DialogButton... buttons) {
        return buttonsProperty().addAll(Arrays.asList(buttons));
    }
    public boolean removeButton(DialogButton button) {
        return buttonsProperty().remove(button);
    }
    public void clearButtons() {
        buttonsProperty().clear();
    }

}
