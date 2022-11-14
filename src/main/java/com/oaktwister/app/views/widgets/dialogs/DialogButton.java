package com.oaktwister.app.views.widgets.dialogs;

import com.oaktwister.app.annotations.ViewDescriptor;
import com.oaktwister.app.events.DialogButtonEvent;
import com.oaktwister.app.services.resources.ViewResources;
import com.oaktwister.app.utils.extensions.FXMLUtil;
import com.oaktwister.app.utils.extensions.PropertyUtil;
import javafx.beans.property.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

@ViewDescriptor(location = ViewResources.Widgets.Dialogs.DIALOG_BUTTON)
public class DialogButton extends AnchorPane implements Initializable {

    @FXML private Button button;

    private final SimpleStringProperty textProperty;
    private final SimpleObjectProperty<EventHandler<DialogButtonEvent>> onActionProperty;
    private final SimpleObjectProperty<DialogResult> resultProperty;

    public DialogButton() {
        textProperty = new SimpleStringProperty();
        resultProperty = new SimpleObjectProperty<>();
        onActionProperty = new SimpleObjectProperty<>();
        FXMLUtil.loadControl(this);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resultProperty.addListener((observableValue, oldValue, newValue) -> {
            String text = null;
            if(newValue != null) {
                text = newValue.getAction();
            }
            textProperty.set(text);
        });
        button.textProperty().bindBidirectional(textProperty);
        button.setOnAction(actionEvent -> {
            DialogButtonEvent event = new DialogButtonEvent(this, actionEvent);
            PropertyUtil.tryHandle(onActionProperty, event);
        });
    }

    public ReadOnlyStringProperty textProperty() {
        return textProperty;
    }
    public String getText() {
        return textProperty().get();
    }

    public ObjectProperty<EventHandler<DialogButtonEvent>> onActionProperty() {
        return onActionProperty;
    }
    public EventHandler<DialogButtonEvent> getOnAction() {
        return onActionProperty().get();
    }
    public void setOnAction(EventHandler<DialogButtonEvent> value) {
        onActionProperty().set(value);
    }

    public ObjectProperty<DialogResult> resultProperty() {
        return resultProperty;
    }
    public DialogResult getResult() {
        return resultProperty().get();
    }
    public void setResult(DialogResult value) {
        resultProperty().set(value);
    }

    public static DialogButton fromResult(DialogResult result) {
        DialogButton button = new DialogButton();
        button.setResult(result);
        return button;
    }

}
