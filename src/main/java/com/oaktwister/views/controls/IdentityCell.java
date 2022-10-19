package com.oaktwister.views.controls;

import com.oaktwister.core.ViewHandler;
import com.oaktwister.services.Resources;
import com.oaktwister.views.View;
import com.oaktwister.views.util.DateTimeStringConverter;
import com.oaktwister.views.util.NumberStringConverter;
import com.oaktwister.views.util.UUIDStringConverter;
import javafx.beans.property.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.UUID;

public class IdentityCell extends AnchorPane implements View {

    private final ViewHandler viewHandler;

    @FXML private Button button;
    @FXML private Label identifierLabel;
    @FXML private Label grantsLabel;
    @FXML private Label createdAtLabel;

    private final SimpleObjectProperty<UUID> identifierProperty;
    private final SimpleIntegerProperty grantsProperty;
    private final SimpleObjectProperty<LocalDateTime> createdAtProperty;

    public IdentityCell(ViewHandler viewHandler) {
        super();
        this.viewHandler = viewHandler;
        this.identifierProperty = new SimpleObjectProperty<UUID>(UUIDStringConverter.empty());
        this.grantsProperty = new SimpleIntegerProperty(-1);
        this.createdAtProperty = new SimpleObjectProperty<>(LocalDateTime.MIN);
        viewHandler.loadCustomView(this);
    }

    @Override
    public String getViewLocation() {
        return Resources.Views.Controls.IDENTITY_CELL;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        identifierLabel.textProperty().bindBidirectional(identifierProperty, new UUIDStringConverter());
        grantsLabel.textProperty().bindBidirectional(grantsProperty, new NumberStringConverter(Integer.class));
        createdAtLabel.textProperty().bindBidirectional(createdAtProperty, new DateTimeStringConverter());
    }

    public ObjectProperty<EventHandler<ActionEvent>> onActionProperty() {
        return button.onActionProperty();
    }

    public SimpleObjectProperty<UUID> identifierProperty() {
        return identifierProperty;
    }

    public IntegerProperty grantsProperty() {
        return grantsProperty;
    }

    public SimpleObjectProperty<LocalDateTime> createdAtProperty() {
        return createdAtProperty;
    }

}
