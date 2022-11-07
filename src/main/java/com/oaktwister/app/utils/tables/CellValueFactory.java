package com.oaktwister.app.utils.tables;

import javafx.beans.value.ObservableValue;
import javafx.beans.value.ObservableValueBase;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class CellValueFactory<T, U> implements Callback<
        TableColumn.CellDataFeatures<T, U>,
        ObservableValue<U>> {

    private final Callback<T, U> callback;

    public CellValueFactory(Callback<T, U> callback) {
        this.callback = callback;
    }

    @Override
    public ObservableValue<U> call(TableColumn.CellDataFeatures<T, U> cellDataFeatures) {
        return new ObservableValueBase<U>() {

            @Override
            public U getValue() {
                T value = cellDataFeatures.getValue();
                return callback.call(value);
            }

        };
    }

}

