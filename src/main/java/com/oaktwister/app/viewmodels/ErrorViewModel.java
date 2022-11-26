package com.oaktwister.app.viewmodels;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public abstract class ErrorViewModel {

    private final SimpleObjectProperty<Exception> errorProperty;

    public ErrorViewModel() {
        errorProperty = new SimpleObjectProperty<>();
    }

    public ReadOnlyObjectProperty<Exception> errorProperty() {
        return errorProperty;
    }
    public Exception getError() {
        return errorProperty().get();
    }
    protected void setError(Exception value) {
        errorProperty.set(value);
    }
    public void clearError() {
        errorProperty.set(null);
    }

}
