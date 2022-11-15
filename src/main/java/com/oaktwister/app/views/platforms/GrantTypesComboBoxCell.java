package com.oaktwister.app.views.platforms;

import com.oaktwister.app.viewmodels.models.DriveViewModel;
import javafx.scene.control.ListCell;

public class GrantTypesComboBoxCell extends ListCell<String> {

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (item == null || empty) {
            super.setText(null);
        } else {
            super.setText(item);
        }
    }

}
