package com.oaktwister.views.windows.login;

import com.oaktwister.viewmodels.models.DriveViewModel;
import javafx.scene.control.ListCell;

public class DriveCell extends ListCell<DriveViewModel> {

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
