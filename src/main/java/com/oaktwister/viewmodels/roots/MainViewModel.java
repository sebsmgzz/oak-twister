package com.oaktwister.viewmodels.roots;

import com.oaktwister.services.logging.Logger;
import com.oaktwister.views.main.Section;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class MainViewModel {

    private final Logger logger;

    private final ObjectProperty<Section> section;

    public MainViewModel(Logger logger) {
        this.logger = logger;
        section = new SimpleObjectProperty<>();
    }

    public ObjectProperty<Section> sectionProperty() {
        return section;
    }

}
