package com.oaktwister.views.controls;

import com.oaktwister.core.ViewHandler;
import com.oaktwister.models.props.Grant;
import com.oaktwister.models.props.GrantMap;
import com.oaktwister.services.Resources;
import com.oaktwister.viewmodels.models.IdentityViewModel;
import com.oaktwister.views.View;
import com.oaktwister.views.util.DateTimeStringConverter;
import com.oaktwister.views.util.NumberStringConverter;
import com.oaktwister.views.util.UUIDStringConverter;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.WeakChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.UUID;

public class IdentityPane extends StackPane implements View {

    private final ViewHandler viewHandler;
    private final IdentityViewModel viewModel;

    @FXML private Button button;
    @FXML private Label identifierLabel;
    @FXML private Label grantsLabel;
    @FXML private Label createdAtLabel;
    @FXML private Button closeButton;

    private final SimpleIntegerProperty grantsCountProperty;
    private final SimpleObjectProperty<GrantMap> grantsProperty;

    public IdentityPane(ViewHandler viewHandler, IdentityViewModel viewModel) {
        super();
        this.viewHandler = viewHandler;
        this.viewModel = viewModel;
        this.grantsCountProperty = new SimpleIntegerProperty(-1);
        this.grantsProperty = new SimpleObjectProperty<GrantMap>();
        viewHandler.loadCustomView(this);
    }

    @Override
    public String getViewLocation() {
        return Resources.Views.Controls.IDENTITY_CELL;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        identifierLabel.textProperty().bindBidirectional(viewModel.idProperty(), new UUIDStringConverter());
        grantsLabel.textProperty().bindBidirectional(grantsCountProperty, new NumberStringConverter(Integer.class));
        createdAtLabel.textProperty().bindBidirectional(viewModel.createdAtProperty(), new DateTimeStringConverter());
        this.setOnMouseEntered(event -> closeButton.setVisible(true));
        this.setOnMouseExited(event -> closeButton.setVisible(false));
    }

    public IdentityViewModel getViewModel() {
        return viewModel;
    }

    public ObjectProperty<EventHandler<ActionEvent>> onActionProperty() {
        return button.onActionProperty();
    }

    public ObjectProperty<EventHandler<ActionEvent>> onCloseProperty() {
        return closeButton.onActionProperty();
    }

    public SimpleObjectProperty<UUID> identifierProperty() {
        return viewModel.idProperty();
    }

    public IntegerProperty grantsCountProperty() {
        return grantsCountProperty;
    }

    public SimpleObjectProperty<LocalDateTime> createdAtProperty() {
        return viewModel.createdAtProperty();
    }

}
