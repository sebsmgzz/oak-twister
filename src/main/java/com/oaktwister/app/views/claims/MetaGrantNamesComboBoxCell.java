package com.oaktwister.app.views.claims;

import javafx.scene.control.ListCell;

public class MetaGrantNamesComboBoxCell extends ListCell<String> {

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
