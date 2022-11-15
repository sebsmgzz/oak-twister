package com.oaktwister.app.views.login;

import com.oaktwister.app.viewmodels.models.DriveViewModel;
import javafx.scene.control.ListCell;

public class DrivesComboBoxCell extends ListCell<DriveViewModel> {

    @Override
    protected void updateItem(DriveViewModel item, boolean empty) {
        super.updateItem(item, empty);
        if (item == null || empty) {
            super.setText(null);
        } else {
            String path = item.pathProperty().get();
            super.setText(path);
        }
    }

}
